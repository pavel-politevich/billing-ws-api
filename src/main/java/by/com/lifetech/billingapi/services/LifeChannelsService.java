package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.TransactionHistory;
import by.com.lifetech.billingapi.models.enums.*;
import by.com.lifetech.billingapi.models.repository.TransactionHistoryRepository;
import by.com.lifetech.billingapi.models.repository.account.AccountExTblRepository;
import by.com.lifetech.billingapi.models.requests.AutopayServiceRequest;
import by.com.lifetech.billingapi.models.requests.BasicTransactionRequest;
import by.com.lifetech.billingapi.models.requests.CheckNonResidentPassportRequest;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import jakarta.persistence.Tuple;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LifeChannelsService {
    Logger logger = LoggerFactory.getLogger(LifeChannelsService.class);

    private final AccountExTblRepository accountExTbl;
    private final ExceptionUtils exceptionUtils;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final ChainService chainService;

    public ServiceResponseDto<CommonCheckAnswer> checkCommonOwner(String msisdn, String checkMsisdn) {
        CommonCheckAnswer answer = CommonCheckAnswer.NO;
        Set<String> documentTypes = EnumSet.allOf(DocumentType.class)
                .stream()
                .filter(type -> type.getCitizenship().equals(Citizenship.RESIDENT))
                .map(DocumentType::name)
                .collect(Collectors.toSet());

        try {
            String passportTypeName = AccountTablesParameters.PASSPORT_TYPE.getFullName();
            List<Tuple> resultList;
            Tuple resultMap;

            resultList = getPassportTypeAndIdentificationCode(msisdn, null);
            if (resultList.isEmpty()) {
                throw new Exception(msisdn + " info not found");
            }
            resultMap = resultList.get(0);
            if (!documentTypes.contains(Optional.ofNullable(resultMap.get(passportTypeName)).orElseThrow())) {
                throw new Exception("Incorrect document type");
            }

            String identificationCode = Optional.ofNullable(resultMap.get(AccountTablesParameters.IDENTIFICATION_CODE.getFullName()))
                    .orElseThrow().toString();
            resultList = getPassportTypeAndIdentificationCode(checkMsisdn, identificationCode);

            if (resultList.isEmpty()) {
                throw new Exception(checkMsisdn + " info not found");
            }
            resultMap = resultList.get(0);

            if (documentTypes.contains(Optional.ofNullable(resultMap.get(passportTypeName)).orElseThrow())) {
                answer = CommonCheckAnswer.YES;
            }
        } catch (Exception e) {
            logger.error("Error when check msisdn owner: {}", e.getMessage());
        }

        ServiceResponseDto<CommonCheckAnswer> response = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        response.setResultMap(answer);
        return response;
    }

    private List<Tuple> getPassportTypeAndIdentificationCode(String msisdn, String identificationCode) {
        Set<String> outputParameters = new HashSet<>();
        Map<String, Object> conditions = new HashMap<>();
        String identificationCodeName = AccountTablesParameters.IDENTIFICATION_CODE.getFullName();
        outputParameters.add(identificationCodeName);
        outputParameters.add(AccountTablesParameters.PASSPORT_TYPE.getFullName());
        conditions.put(AccountTablesParameters.MOBILE_NO.getFullName(), msisdn);

        if (identificationCode != null) {
            conditions.put(identificationCodeName, identificationCode);
        }

        return accountExTbl.findAccountInfoByConditionsTuple(conditions,outputParameters);
    }

    public ServiceResponseDto<CommonCheckAnswer> checkIsServiceWasActEarlier(String msisdn, String omCode) throws BusinessException, InternalException {
        CommonCheckAnswer answer = CommonCheckAnswer.NO;

        String contractId = getActiveContractCode(msisdn);

        if (transactionHistoryRepository.findServiceAnyActivation(msisdn, contractId, omCode).isPresent()) {
            answer = CommonCheckAnswer.YES;
        }

        ServiceResponseDto<CommonCheckAnswer> response = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        response.setResultMap(answer);
        return response;
    }

    private String getActiveContractCode(String msisdn) throws BusinessException, InternalException {
        List<Tuple> accountInfoList = getContractCodeByMsisdn(msisdn);
        if (accountInfoList.isEmpty()) {
            throw exceptionUtils.getMsisdnNotFoundException();
        }
        if (accountInfoList.size() > 1) {
            throw new InternalException("Incorrect contracts count");
        }
        return Optional.ofNullable(accountInfoList.get(0).get(AccountTablesParameters.CONTRACT_ID.getFullName()))
                .orElseThrow().toString();
    }

    private List<Tuple> getContractCodeByMsisdn(String msisdn) {
        Set<String> outputParameters = new HashSet<>();
        Map<String, Object> conditions = new HashMap<>();
        outputParameters.add(AccountTablesParameters.CONTRACT_ID.getFullName());
        conditions.put(AccountTablesParameters.MOBILE_NO.getFullName(), msisdn);

        return accountExTbl.findAccountInfoByConditionsTuple(conditions,outputParameters);
    }

    public ServiceResponseDto<Boolean> checkNonResidentPassport(CheckNonResidentPassportRequest request) {
        ServiceResponseDto<Boolean> response = new ServiceResponseDto<Boolean>().setDefaultSuccessResponse();
        response.setResultMap(false);
        List<Tuple> passportDataList = getPassportFIOByMsisdnForNonResident(request.getMsisdn());

        if (passportDataList.isEmpty()) {
            return response;
        }

        Tuple passportDataMap = passportDataList.get(0);
        boolean isEqualFirstName = checkAccountDataEqualsIgnoreCase(request.getGivenNames(), passportDataMap
                , AccountTablesParameters.FIRST_NAME.getFullName());
        boolean isEqualLastName = checkAccountDataEqualsIgnoreCase(request.getFamilyNames(), passportDataMap
                , AccountTablesParameters.LAST_NAME.getFullName());
        boolean isEqualPassportNumber = checkAccountDataEqualsIgnoreCase(request.getPassportNumber(), passportDataMap
                , AccountTablesParameters.PASSPORT_NUMBER.getFullName());
        boolean isEqualPassportSeries = checkAccountDataEqualsIgnoreCase(request.getPassportSeries(), passportDataMap
                , AccountTablesParameters.PASSPORT_SERIES.getFullName());

        if (isEqualFirstName && isEqualLastName && isEqualPassportNumber && isEqualPassportSeries) {
            response.setResultMap(true);
        } else {
            logger.info("Result of passport checking: firstName: {}, lastName: {}, passportNumber: {}, passportSeries: {}"
                    , isEqualFirstName, isEqualLastName, isEqualPassportNumber, isEqualPassportSeries);
        }

        return response;
    }

    private boolean checkAccountDataEqualsIgnoreCase(String checkedData, Tuple accountDataMap, String accountDataKey) {
        return checkedData.equalsIgnoreCase(Optional
                .ofNullable(accountDataMap.get(accountDataKey)).orElse("").toString());
    }

    private List<Tuple> getPassportFIOByMsisdnForNonResident(String msisdn) {
        Set<String> outputParameters = new HashSet<>();
        Map<String, Object> conditions = new HashMap<>();
        outputParameters.add(AccountTablesParameters.FIRST_NAME.getFullName());
        outputParameters.add(AccountTablesParameters.LAST_NAME.getFullName());
        outputParameters.add(AccountTablesParameters.PASSPORT_NUMBER.getFullName());
        outputParameters.add(AccountTablesParameters.PASSPORT_SERIES.getFullName());
        conditions.put(AccountTablesParameters.MOBILE_NO.getFullName(), msisdn);

        return accountExTbl.findAccountInfoByConditionsTuple(conditions,outputParameters);
    }

    public ServiceResponseDto<Map<String, BigDecimal>> getAmountForAutoPayment(String msisdn) throws InternalException, BusinessException {
        ServiceResponseDto<Map<String, BigDecimal>> response = new ServiceResponseDto<Map<String, BigDecimal>>().setDefaultSuccessResponse();

        ChainResult chainResult = chainService.executeChain(ChainType.OM, "autopayment_amount", Map.of("msisdn", msisdn));
        BigDecimal amount = new BigDecimal(ChainResultToServiceResponseConverter
                .getObjectFromResultList(chainResult, "amount").toString());

        response.setResultMap(Map.of("amount", amount));
        return response;
    }

    public ServiceResponseDto<Map<String, Long>> insertTransactionHistory(BasicTransactionRequest request) {
        ServiceResponseDto<Map<String, Long>> serviceResponse = new ServiceResponseDto<Map<String, Long>>().setDefaultSuccessResponse();

        if (!Optional.ofNullable(request.getBMobileNo()).orElse("").isBlank()
                && Optional.ofNullable(request.getBContractCode()).orElse("").isBlank()) {
            throw new ValidationException("Not received contract code for msisdn_b");
        }

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAMobileNo(request.getAMobileNo());
        transactionHistory.setAContractCode(request.getAContractCode());
        transactionHistory.setComments(request.getComments());
        transactionHistory.setTransactionTypeCode(request.getTransactionTypeCode());
        transactionHistory.setAgentName(request.getAgentName());
        transactionHistory.setEntryDate(LocalDateTime.now());
        transactionHistory.setBContractCode(request.getBContractCode());
        transactionHistory.setBMobileNo(request.getBMobileNo());
        transactionHistoryRepository.save(transactionHistory);

        serviceResponse.setResultMap(Map.of("thCode", transactionHistory.getId()));
        return serviceResponse;
    }

    public ServiceResponseDto<Map<String, Object>> activateAutoPayMainService(AutopayServiceRequest req) throws BusinessException, InternalException {
        return callAutopaymentServiceChain("autopayment_main", "act", req);
    }

    public ServiceResponseDto<Map<String, Object>> deactivateAutoPayMainService(AutopayServiceRequest req) throws BusinessException, InternalException {
        return callAutopaymentServiceChain("autopayment_main", "deact", req);
    }

    public ServiceResponseDto<Map<String, Object>> activateAutoPayRecipientService(AutopayServiceRequest req) throws BusinessException, InternalException {
        return callAutopaymentServiceChain("autopayment_recipient", "act", req);
    }

    public ServiceResponseDto<Map<String, Object>> deactivateAutoPayRecipientService(AutopayServiceRequest req) throws BusinessException, InternalException {
        return callAutopaymentServiceChain("autopayment_recipient", "deact", req);
    }

    private ServiceResponseDto<Map<String, Object>> callAutopaymentServiceChain(String service, String operation, AutopayServiceRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", req.getMsisdn());
        map.put("productOfferingCode", "S_" + service.toUpperCase());
        map.put("channel", req.getChannel());
        if (service.equals("autopayment_recipient")) {
            map.put("main_msisdn", req.getMainMsisdn());
        }

        ChainResult chainResult = chainService.executeChain(ChainType.OM, String.format("%s_%s", service, operation), map);
        return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
    }
}
