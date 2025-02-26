package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.IPayException;
import by.com.lifetech.billingapi.models.dto.IPayFinancialTransactionDto;
import by.com.lifetech.billingapi.models.dto.ipay.SubscriberFIODto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.IPayOnlineTransaction;
import by.com.lifetech.billingapi.models.enums.*;
import by.com.lifetech.billingapi.models.repository.IPayOnlineTransactionRepository;
import by.com.lifetech.billingapi.models.repository.IPayOnlineTransactionVRepository;
import by.com.lifetech.billingapi.models.repository.account.AccountExTblRepository;
import by.com.lifetech.billingapi.models.requests.IPayChargingRequest;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class IPayService {
    Logger logger = LoggerFactory.getLogger(IPayService.class);

    private final ChainService chainService;
    private final AccountExTblRepository accountExTbl;
    private final IPayOnlineTransactionVRepository iPayOnlineTransactionVRepository;
    private final IPayOnlineTransactionRepository iPayOnlineTransactionRepository;
    private final String IPAY_CHANNEL = "iPay";

    public ServiceResponseDto<Map<String, Object>> getServiceInfo(String msisdn, BigDecimal amount) throws IPayException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        map.put("amount", amount);

        return callCommonIpayChain("iPayGetServiceInfo", map);
    }

    public ServiceResponseDto<Map<String, Object>> checkSubscriberBalance(String msisdn) throws IPayException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);

        return callCommonIpayChain("checkSubscriberBalance", map);
    }

    private ServiceResponseDto<Map<String, Object>> callCommonIpayChain(String chainName, Map<String, Object> map) throws IPayException {
        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.FM, chainName, map);
        } catch (BusinessException e) {
            throw new IPayException("", e.getError().getErrorName());
        } catch (Exception e) {
            throw new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);
        }

        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<SubscriberFIODto> getSubscriberFIO(String msisdn) throws IPayException {
        Set<String> outputParameters = getOutputParametersSubsciberFIO();
        Map<String, Object> conditions = new HashMap<>();
        conditions.put(AccountTablesParameters.MOBILE_NO.getFullName(), msisdn);
        Tuple resultMap;

        try {
            resultMap = accountExTbl.findAccountInfoByConditionsTuple(conditions,outputParameters).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IPayException(IPayErrorCode.ERR40001);
        } catch (Exception e) {
            throw new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);
        }

        if (resultMap.get(AccountTablesParameters.CONTRACT_TYPE.getFullName()).equals(ContractType.CORP.name())) {
            throw new IPayException(IPayErrorCode.ERR_TP_000);
        }

        SubscriberFIODto subscriberFIO = getSubscriberFIODto(resultMap);
        if (subscriberFIO.getFirstName().isEmpty() && subscriberFIO.getFamilyName().isEmpty()
                && subscriberFIO.getPatronymic().isEmpty()) {
            throw new IPayException(IPayErrorCode.FIO_NOT_FOUND);
        }

        ServiceResponseDto response = new ServiceResponseDto().setDefaultSuccessResponse();
        response.setResultMap(subscriberFIO);
        return response;
    }

    private SubscriberFIODto getSubscriberFIODto(Tuple resultMap) {
        String firstName = Optional.ofNullable(resultMap
                .get(AccountTablesParameters.FIRST_NAME.getFullName())).orElse("").toString();
        String lastName = Optional.ofNullable(resultMap
                .get(AccountTablesParameters.LAST_NAME.getFullName())).orElse("").toString();
        String midName = Optional.ofNullable(resultMap
                .get(AccountTablesParameters.MID_NAME.getFullName())).orElse("").toString();
        return new SubscriberFIODto(firstName, midName, lastName);
    }

    private Set<String> getOutputParametersSubsciberFIO() {
        Set<String> outputParameters = new HashSet<>();
        outputParameters.add(AccountTablesParameters.FIRST_NAME.getFullName());
        outputParameters.add(AccountTablesParameters.LAST_NAME.getFullName());
        outputParameters.add(AccountTablesParameters.MID_NAME.getFullName());
        outputParameters.add(AccountTablesParameters.CONTRACT_TYPE.getFullName());
        return outputParameters;
    }

    public ServiceResponseDto<Map<String, Object>> checkRequestStatus(String operId) throws IPayException {
        List<String> depositStatusList;
        try {
            depositStatusList = iPayOnlineTransactionVRepository.getDepositStatusByTransactionId(operId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IPayException("Invalid request for operId", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        if (depositStatusList.isEmpty()) {
            throw new IPayException("No response returned for operId", IPayErrorCode.NONRECOVERABLE_ERROR.name());
        }

        if (depositStatusList.size() > 1 || depositStatusList.get(0) == null) {
            throw new IPayException("Too many records returned for operId", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        ServiceResponseDto response = new ServiceResponseDto().setDefaultSuccessResponse();
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("depositStatus", depositStatusList.get(0));
        response.setResultMap(responseMap);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> iPayMoneyBack(String operId) throws IPayException {
        List<IPayFinancialTransactionDto> iPayFinancialTransactions = getIPayFinancialTransactions(operId, true);

        String creditCode = checkIPayTransactionInfoMoneyBack(iPayFinancialTransactions);

        Map<String, Object> map = new HashMap<>();
        map.put("transactionId", operId);
        map.put("channelUser", IPAY_CHANNEL);
        IPayException callChainException = null;
        ServiceResponseDto<Map<String, Object>> moneyBackResult = null;
        try {
            moneyBackResult = callCommonIpayChain("iPayMoneyBack", map);
        } catch (IPayException e) {
            callChainException = e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            callChainException = new IPayException("Invalid response for money back processing", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        try {
            saveMoneyBackTransactionResult(operId, creditCode
                    , callChainException == null ? IPayOperationResponse.MONEY_BACK_SUCCESS : IPayOperationResponse.MONEY_BACK_ERROR);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IPayException("Unsuccessful save money back transaction", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        if (callChainException != null) {
            throw callChainException;
        }

        return moneyBackResult;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void saveMoneyBackTransactionResult(String operId, String code, IPayOperationResponse result) {
        IPayOnlineTransaction iPayOnlineTransaction;
        if (code == null) {
            iPayOnlineTransaction = new IPayOnlineTransaction();
            iPayOnlineTransaction.setEntryDate(LocalDateTime.now());
            iPayOnlineTransaction.setAuditDate(LocalDateTime.now());
            iPayOnlineTransaction.setTransactionId(operId);
            iPayOnlineTransaction.setOperationTypeCode(IPayOperationType.IOT_CREDIT.name());
        } else {
            iPayOnlineTransaction = iPayOnlineTransactionRepository.findByCode(code).orElseThrow();
        }
        iPayOnlineTransaction.setOperationResponseCode(result.getCode());
        iPayOnlineTransactionRepository.save(iPayOnlineTransaction);
    }

    private String checkIPayTransactionInfoMoneyBack(List<IPayFinancialTransactionDto> iPayFinancialTransactions) throws IPayException {
        IPayFinancialTransactionDto debitTransaction;
        String creditCode = null;
        if (iPayFinancialTransactions.size() == 1) {
            debitTransaction = iPayFinancialTransactions.stream()
                    .filter(i -> IPayOperationType.IOT_DEBIT.name().equals(i.getOperationTypeCode()) &&
                            IPayOperationResponse.CHARGING_SUCCESS.getCode().equals(i.getOperationResponseCode())
                    )
                    .findFirst()
                    .orElse(null);
        } else {
            debitTransaction = iPayFinancialTransactions.stream()
                    .filter(i -> IPayOperationType.IOT_DEBIT.name().equals(i.getOperationTypeCode()))
                    .findFirst()
                    .orElse(null);
            creditCode = iPayFinancialTransactions.stream()
                    .filter(i -> IPayOperationType.IOT_CREDIT.name().equals(i.getOperationTypeCode()) &&
                            !IPayOperationResponse.MONEY_BACK_SUCCESS.getCode().equals(i.getOperationResponseCode())
                    )
                    .map(IPayFinancialTransactionDto::getCode)
                    .findFirst()
                    .orElse(null);
        }

        if (debitTransaction == null || (iPayFinancialTransactions.size() == 2 && creditCode == null)) {
            throw new IPayException("Incorrect transaction data returned for money back operation", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        return creditCode;
    }

    private List<IPayFinancialTransactionDto> getIPayFinancialTransactions(String operId, boolean notEmpty) throws IPayException {
        List<IPayFinancialTransactionDto> iPayFinancialTransactions;
        try {
            iPayFinancialTransactions = iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(operId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IPayException("Invalid request for operId", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        if (notEmpty && iPayFinancialTransactions.isEmpty()) {
            throw new IPayException("No response returned for operId", IPayErrorCode.NONRECOVERABLE_ERROR.name());
        }

        if (iPayFinancialTransactions.size() > 2) {
            throw new IPayException("Too many records returned for operId", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        return iPayFinancialTransactions;
    }

    public ServiceResponseDto<Map<String, Object>> iPayCharging(IPayChargingRequest iPayChargingRequest) throws IPayException {
        List<IPayFinancialTransactionDto> iPayFinancialTransactions = getIPayFinancialTransactions(iPayChargingRequest.getOperID(), false);

        String debitCode = checkIPayTransactionInfoCharging(iPayFinancialTransactions);

        Map<String, Object> map = new HashMap<>();
        map.put("transactionId", iPayChargingRequest.getOperID());
        map.put("msisdn", iPayChargingRequest.getMsisdn());
        map.put("amount", iPayChargingRequest.getAmount());
        map.put("channelUser", IPAY_CHANNEL);
        IPayException callChainException = null;
        ServiceResponseDto<Map<String, Object>> chargingResult = null;
        try {
            chargingResult = callCommonIpayChain("iPayCharging", map);
        } catch (IPayException e) {
            callChainException = e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            callChainException = new IPayException("Invalid response for charging processing", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        String fhCode = "";
        if (callChainException == null && chargingResult.getResultMap() != null) {
            fhCode = Optional.ofNullable(chargingResult.getResultMap()
                    .get("fhCode")).orElse("").toString();
        }

        try {
            saveChargingTransactionResult(iPayChargingRequest, debitCode, fhCode, callChainException);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IPayException("Unsuccessful save charging transaction", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        if (callChainException != null) {
            throw callChainException;
        }

        return chargingResult;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void saveChargingTransactionResult(IPayChargingRequest iPayChargingRequest, String debitCode, String fhCode, IPayException callChainException) {
        IPayOnlineTransaction iPayOnlineTransaction;
        boolean isSuccessOperation = !fhCode.isEmpty() && callChainException == null;
        String operationResult = isSuccessOperation ? IPayOperationResponse.CHARGING_SUCCESS.getCode()
                : callChainException.getIPayErrorCode().getIPayErrorCode();
        if (debitCode == null) {
            iPayOnlineTransaction = new IPayOnlineTransaction();
            iPayOnlineTransaction.setEntryDate(LocalDateTime.now());
            iPayOnlineTransaction.setAuditDate(LocalDateTime.now());
            iPayOnlineTransaction.setTransactionId(iPayChargingRequest.getOperID());
            iPayOnlineTransaction.setMsisdn(iPayChargingRequest.getMsisdn());
            iPayOnlineTransaction.setAmount(iPayChargingRequest.getAmount());
            iPayOnlineTransaction.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        } else {
            iPayOnlineTransaction = iPayOnlineTransactionRepository.findByCode(debitCode).orElseThrow();
        }

        if (isSuccessOperation) {
            iPayOnlineTransaction.setFinancialHistoryCode(fhCode);
        }
        iPayOnlineTransaction.setOperationResponseCode(operationResult);
        iPayOnlineTransactionRepository.save(iPayOnlineTransaction);
    }

    private String checkIPayTransactionInfoCharging(List<IPayFinancialTransactionDto> iPayFinancialTransactions) throws IPayException {
        if (iPayFinancialTransactions.isEmpty()) {
            return null;
        }

        if (iPayFinancialTransactions.size() > 1) {
            throw new IPayException("Too many records returned for operId", IPayErrorCode.GET_SERV_INFO_ERROR.name());
        }

        IPayFinancialTransactionDto transaction = iPayFinancialTransactions.get(0);
        if (!transaction.getOperationTypeCode().equals(IPayOperationType.IOT_DEBIT.name())) {
            throw new IPayException("Invalid operation type returned for operId", IPayErrorCode.NONRECOVERABLE_ERROR.name());
        }

        if (transaction.getOperationResponseCode().equals(IPayOperationResponse.CHARGING_SUCCESS.getCode())) {
            throw new IPayException(IPayErrorCode.OPERATION_ALREADY_PERFORMED);
        }

        return transaction.getCode();
    }
}
