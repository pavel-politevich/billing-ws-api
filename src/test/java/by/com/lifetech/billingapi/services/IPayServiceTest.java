package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.IPayException;
import by.com.lifetech.billingapi.exceptions.InternalException;
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
import by.com.lifetech.billingapi.utils.test.MockTuple;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.com.lifetech.billingapi.wsdl.ChainResultElement;
import by.com.lifetech.billingapi.wsdl.ResultCodeType;
import jakarta.persistence.RollbackException;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IPayServiceTest {
    @Mock
    private ChainService chainService;
    @Mock
    private AccountExTblRepository accountExTbl;
    @Mock
    private IPayOnlineTransactionVRepository iPayOnlineTransactionVRepository;
    @Mock
    private IPayOnlineTransactionRepository iPayOnlineTransactionRepository;

    @InjectMocks
    private IPayService iPayService;

    @Test
    @DisplayName("Return success response for iPay service info chain")
    void getServiceInfo_whenSuccessChainAnswer_thenReturnSuccessResponse() throws Exception {
        ChainResult chainResult = new ChainResult();
        when(chainService.executeChain(any(), any(), any())).thenReturn(chainResult);
        ServiceResponseDto<Map<String, Object>> expectedResponse = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);

        ServiceResponseDto<Map<String, Object>> actualResponse = iPayService.getServiceInfo("375256257275", BigDecimal.ZERO);

        assertEquals(expectedResponse, actualResponse);
        verify(chainService, times(1)).executeChain(any(), any(), any());
    }

    @Test
    @DisplayName("Throw iPay exception for business error of chain")
    void getServiceInfo_whenBusinessErrorChainResult_thenThrowIPayException() throws Exception {
        BusinessException businessException = new BusinessException("ERR40001", "MSISDN не найден");
        when(chainService.executeChain(any(), any(), any())).thenThrow(businessException);

        assertThrows(IPayException.class, () -> {
            iPayService.getServiceInfo("375256257275", BigDecimal.ZERO);
        });
        verify(chainService, times(1)).executeChain(any(), any(), any());
    }

    @Test
    @DisplayName("Throw iPay service exception for not business error of chain")
    void getServiceInfo_whenRandomErrorChainResult_thenThrowIPayServiceError() throws Exception {
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);
        when(chainService.executeChain(any(), any(), any())).thenThrow(new InternalException(""));

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.getServiceInfo("375256257275", BigDecimal.ZERO);
        });

        assertEquals(expectedIPayException, actualIPayException);
        verify(chainService, times(1)).executeChain(any(), any(), any());
    }

    @Test
    @DisplayName("Return response for check subscriber balance")
    void checkSubscriberBalance_whenSuccessChainAnswer_thenReturnSuccessResponse() throws Exception {
        ChainResult chainResult = new ChainResult();
        when(chainService.executeChain(any(), any(), any())).thenReturn(chainResult);
        ServiceResponseDto<Map<String, Object>> expectedResponse = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);

        ServiceResponseDto<Map<String, Object>> actualResponse = iPayService.checkSubscriberBalance("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(chainService, times(1)).executeChain(any(), any(), any());
    }

    @Test
    @DisplayName("Throw exception when not found msisdn for search FIO")
    void getSubscriberFIO_whenMsisdnNotFound_thenThrowIPayException() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any())).thenReturn(new ArrayList<>());
        IPayException expectedIPayException = new IPayException(IPayErrorCode.ERR40001);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
           iPayService.getSubscriberFIO("375256257275");
        });

        assertEquals(expectedIPayException, actualIPayException);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Return iPay error when throw exception for DB search FIO")
    void getSubscriberFIO_whenErrorInDbAnswer_thenThrowIPayException() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any())).thenThrow(new RollbackException());
        assertThrows(IPayException.class, () -> {
            iPayService.getSubscriberFIO("375256257275");
        });
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Throw exception for corporate contract from search FIO")
    void getSubscriberFIO_whenCorpContractType_thenThrowIPayException() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithContractType(ContractType.CORP.name()));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.ERR_TP_000);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.getSubscriberFIO("375256257275");
        });

        assertEquals(expectedIPayException, actualIPayException);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Throw exception for empty FIO")
    void getSubscriberFIO_whenFIOEmpty_thenThrowIPayException() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithContractType(ContractType.IND.name()));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.FIO_NOT_FOUND);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.getSubscriberFIO("375256257275");
        });

        assertEquals(expectedIPayException, actualIPayException);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Return FIO for some empty FIO fields")
    void getSubscriberFIO_whenFIOPartEmpty_thenReturnResponse() throws Exception {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithFIO("Qa", "Ws", null));
        ServiceResponseDto<SubscriberFIODto> expectedResponse = new ServiceResponseDto<SubscriberFIODto>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(new SubscriberFIODto("Qa", "", "Ws"));

        ServiceResponseDto<SubscriberFIODto> actualResponse = iPayService.getSubscriberFIO("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Return subscriber FIO")
    void getSubscriberFIO_whenFullFIOFounded_thenReturnResponse() throws Exception {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithFIO("Qa", "Ws", "Ed"));
        ServiceResponseDto<SubscriberFIODto> expectedResponse = new ServiceResponseDto<SubscriberFIODto>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(new SubscriberFIODto("Qa", "Ed", "Ws"));

        ServiceResponseDto<SubscriberFIODto> actualResponse = iPayService.getSubscriberFIO("375256257275");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Throw iPay exception for DB error of get status")
    void checkRequestStatus_whenDBError_thenThrowIPayException() {
       when(iPayOnlineTransactionVRepository.getDepositStatusByTransactionId(anyString()))
                .thenThrow(new RollbackException());
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.checkRequestStatus("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionVRepository, times(1)).getDepositStatusByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception for not found status")
    void checkRequestStatus_whenStatusNotFound_thenThrowIPayException() {
        when(iPayOnlineTransactionVRepository.getDepositStatusByTransactionId(anyString())).thenReturn(new ArrayList<>());
        IPayException expectedIPayException = new IPayException(IPayErrorCode.NONRECOVERABLE_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.checkRequestStatus("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionVRepository, times(1)).getDepositStatusByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception for many rows by status response")
    void checkRequestStatus_whenMoreThanOneStatusRows_thenThrowIPayException() {
        when(iPayOnlineTransactionVRepository.getDepositStatusByTransactionId(anyString())).thenReturn(List.of("", ""));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.checkRequestStatus("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionVRepository, times(1)).getDepositStatusByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception for null status response")
    void checkRequestStatus_whenStatusIsNull_thenThrowIPayException() {
        List<String> list = new ArrayList<>();
        list.add(null);
        when(iPayOnlineTransactionVRepository.getDepositStatusByTransactionId(anyString())).thenReturn(list);
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.checkRequestStatus("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionVRepository, times(1)).getDepositStatusByTransactionId(anyString());
    }

    @Test
    @DisplayName("Get response with valid status")
    void checkRequestStatus_whenGetValidStatus_thenReturnResponse() throws Exception {
        when(iPayOnlineTransactionVRepository.getDepositStatusByTransactionId(anyString())).thenReturn(List.of("1"));
        ServiceResponseDto<Map<String, Object>> expectedResponse = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("depositStatus", "1");
        expectedResponse.setResultMap(responseMap);

        ServiceResponseDto<Map<String, Object>> actualResponse = iPayService.checkRequestStatus("12345");

        assertEquals(expectedResponse, actualResponse);
        verify(iPayOnlineTransactionVRepository, times(1)).getDepositStatusByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception for DB error of money back search transaction")
    void iPayMoneyBack_whenGetTransactionDBError_thenThrowIPayException() {
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenThrow(new RollbackException());
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayMoneyBack("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception when not found transaction for money back")
    void iPayMoneyBack_whenTransactionNotFound_thenThrowIPayException() {
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString())).thenReturn(new ArrayList<>());
        IPayException expectedIPayException = new IPayException(IPayErrorCode.NONRECOVERABLE_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayMoneyBack("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception for many rows of transaction for money back")
    void iPayMoneyBack_whenMoreThanTwoTransactionsRows_thenThrowIPayException() {
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(new IPayFinancialTransactionDto(), new IPayFinancialTransactionDto(), new IPayFinancialTransactionDto()));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayMoneyBack("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception when found one transaction and it isn't success debit (for money back)")
    void iPayMoneyBack_whenOneTransactionNotSuccessDebit_thenThrowIPayException() {
        IPayFinancialTransactionDto transaction = new IPayFinancialTransactionDto();
        transaction.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transaction.setOperationResponseCode("err");
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transaction));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayMoneyBack("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception when found two transactions and it has success credit (for money back)")
    void iPayMoneyBack_whenTwoTransactionsAndHasSuccessCredit_thenThrowIPayException() {
        IPayFinancialTransactionDto transactionDebit = new IPayFinancialTransactionDto();
        transactionDebit.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transactionDebit.setOperationResponseCode(IPayOperationResponse.CHARGING_SUCCESS.getCode());
        IPayFinancialTransactionDto transactionCredit = new IPayFinancialTransactionDto();
        transactionCredit.setOperationTypeCode(IPayOperationType.IOT_CREDIT.name());
        transactionCredit.setOperationResponseCode(IPayOperationResponse.MONEY_BACK_SUCCESS.getCode());
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transactionDebit, transactionCredit));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayMoneyBack("12345");
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception and save result to db when money back chain return error")
    void iPayMoneyBack_whenChainThrowException_thenThrowIPayExceptionAndSaveResult() throws Exception {
        IPayFinancialTransactionDto transactionDebit = new IPayFinancialTransactionDto();
        transactionDebit.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transactionDebit.setOperationResponseCode(IPayOperationResponse.CHARGING_SUCCESS.getCode());
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transactionDebit));
        when(chainService.executeChain(any(), any(), any())).thenThrow(new BusinessException("", ""));
        when(iPayOnlineTransactionRepository.save(any())).thenReturn(new IPayOnlineTransaction());

        assertThrows(IPayException.class, () -> {
            iPayService.iPayMoneyBack("12345");
        });
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(0)).findByCode(any());
    }

    @Test
    @DisplayName("Throw iPay exception for unsuccessful save result to db when money back chain return error")
    void iPayMoneyBack_whenSaveDbError_thenThrowIPayException() throws Exception {
        IPayFinancialTransactionDto transactionDebit = new IPayFinancialTransactionDto();
        transactionDebit.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transactionDebit.setOperationResponseCode(IPayOperationResponse.CHARGING_SUCCESS.getCode());
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transactionDebit));
        when(chainService.executeChain(any(), any(), any())).thenReturn(new ChainResult());
        when(iPayOnlineTransactionRepository.save(any())).thenThrow(new RollbackException());

        assertThrows(IPayException.class, () -> {
            iPayService.iPayMoneyBack("12345");
        });
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(0)).findByCode(any());
    }

    @Test
    @DisplayName("Return success response and save result to db when money back chain completed successfully and it is first money back")
    void iPayMoneyBack_whenSuccessResponseFromChainAndFirstMoneyBack_thenReturnResponse() throws Exception {
        ChainResult chainResult = new ChainResult();
        IPayFinancialTransactionDto transactionDebit = new IPayFinancialTransactionDto();
        transactionDebit.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transactionDebit.setOperationResponseCode(IPayOperationResponse.CHARGING_SUCCESS.getCode());
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transactionDebit));
        when(chainService.executeChain(any(), any(), any())).thenReturn(new ChainResult());
        when(iPayOnlineTransactionRepository.save(any())).thenReturn(new IPayOnlineTransaction());
        ServiceResponseDto<Map<String, Object>> expectedResponse = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);

        ServiceResponseDto<Map<String, Object>> actualResponse = iPayService.iPayMoneyBack("12345");

        assertEquals(expectedResponse, actualResponse);
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(0)).findByCode(any());
    }

    @Test
    @DisplayName("Return success response and save result to db when money back chain completed successfully and it repeated money back")
    void iPayMoneyBack_whenSuccessResponseFromChainAndRetryMoneyBack_thenReturnResponse() throws Exception {
        ChainResult chainResult = new ChainResult();
        IPayFinancialTransactionDto transactionDebit = new IPayFinancialTransactionDto();
        transactionDebit.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transactionDebit.setOperationResponseCode(IPayOperationResponse.CHARGING_SUCCESS.getCode());
        IPayFinancialTransactionDto transactionCredit = new IPayFinancialTransactionDto();
        transactionCredit.setOperationTypeCode(IPayOperationType.IOT_CREDIT.name());
        transactionCredit.setOperationResponseCode(IPayOperationResponse.MONEY_BACK_ERROR.getCode());
        transactionCredit.setCode("111");
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transactionDebit, transactionCredit));
        when(chainService.executeChain(any(), any(), any())).thenReturn(new ChainResult());
        when(iPayOnlineTransactionRepository.save(any())).thenReturn(new IPayOnlineTransaction());
        when(iPayOnlineTransactionRepository.findByCode(anyString())).thenReturn(Optional.of(new IPayOnlineTransaction()));
        ServiceResponseDto<Map<String, Object>> expectedResponse = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);

        ServiceResponseDto<Map<String, Object>> actualResponse = iPayService.iPayMoneyBack("12345");

        assertEquals(expectedResponse, actualResponse);
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(1)).findByCode(any());
    }

    @Test
    @DisplayName("Throw iPay exception when found one transaction and it isn't debit (for charging)")
    void iPayCharging_whenOneTransactionNotDebit_thenThrowIPayException() {
        IPayFinancialTransactionDto transaction = new IPayFinancialTransactionDto();
        transaction.setOperationTypeCode(IPayOperationType.IOT_CREDIT.name());
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transaction));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.NONRECOVERABLE_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayCharging(new IPayChargingRequest("12345", "375256257275", BigDecimal.ZERO));
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception when found one transaction and it is success debit (for charging)")
    void iPayCharging_whenOneTransactionSuccessDebit_thenThrowIPayException() {
        IPayFinancialTransactionDto transaction = new IPayFinancialTransactionDto();
        transaction.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transaction.setOperationResponseCode(IPayOperationResponse.CHARGING_SUCCESS.getCode());
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transaction));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.OPERATION_ALREADY_PERFORMED);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayCharging(new IPayChargingRequest("12345", "375256257275", BigDecimal.ZERO));
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception when found two transactions (for charging)")
    void iPayCharging_whenFoundTwoTransactions_thenThrowIPayException() {
        IPayFinancialTransactionDto transactionDebit = new IPayFinancialTransactionDto();
        transactionDebit.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transactionDebit.setOperationResponseCode(IPayOperationResponse.CHARGING_SUCCESS.getCode());
        IPayFinancialTransactionDto transactionCredit = new IPayFinancialTransactionDto();
        transactionCredit.setOperationTypeCode(IPayOperationType.IOT_CREDIT.name());
        transactionCredit.setOperationResponseCode(IPayOperationResponse.MONEY_BACK_SUCCESS.getCode());
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transactionDebit, transactionCredit));
        IPayException expectedIPayException = new IPayException(IPayErrorCode.GET_SERV_INFO_ERROR);

        IPayException actualIPayException = assertThrows(IPayException.class, () -> {
            iPayService.iPayCharging(new IPayChargingRequest("12345", "375256257275", BigDecimal.ZERO));
        });

        assertEquals(expectedIPayException.getIPayErrorCode(), actualIPayException.getIPayErrorCode());
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
    }

    @Test
    @DisplayName("Throw iPay exception and save result to db when charging chain return error")
    void iPayCharging_whenChainThrowException_thenThrowIPayExceptionAndSaveResult() throws Exception {
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(new ArrayList<>());
        when(chainService.executeChain(any(), any(), any())).thenThrow(new BusinessException("", ""));
        when(iPayOnlineTransactionRepository.save(any())).thenReturn(new IPayOnlineTransaction());

        assertThrows(IPayException.class, () -> {
            iPayService.iPayCharging(new IPayChargingRequest("12345", "375256257275", BigDecimal.ZERO));
        });
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(0)).findByCode(any());
    }

    @Test
    @DisplayName("Throw iPay exception for unsuccessful save result to db when charging chain return error")
    void iPayCharging_whenSaveDbError_thenThrowIPayException() throws Exception {
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(new ArrayList<>());
        when(chainService.executeChain(any(), any(), any())).thenThrow(new BusinessException("", ""));
        when(iPayOnlineTransactionRepository.save(any())).thenThrow(new RollbackException());

        assertThrows(IPayException.class, () -> {
            iPayService.iPayCharging(new IPayChargingRequest("12345", "375256257275", BigDecimal.ZERO));
        });
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(0)).findByCode(any());
    }

    @Test
    @DisplayName("Return fhCode for success retry iPay charging")
    void iPayCharging_whenSuccessRetryCharging_thenReturnResponse() throws Exception {
        ChainResult chainResult = getIPayChargingSuccessChainResult();
        IPayFinancialTransactionDto transaction = new IPayFinancialTransactionDto();
        transaction.setOperationTypeCode(IPayOperationType.IOT_DEBIT.name());
        transaction.setOperationResponseCode("err");
        transaction.setCode("123");
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(List.of(transaction));
        when(chainService.executeChain(any(), any(), any())).thenReturn(chainResult);
        when(iPayOnlineTransactionRepository.save(any())).thenReturn(new IPayOnlineTransaction());
        when(iPayOnlineTransactionRepository.findByCode(anyString())).thenReturn(Optional.of(new IPayOnlineTransaction()));
        ServiceResponseDto<Map<String, Object>> expectedResponse = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);

        ServiceResponseDto<Map<String, Object>> actualResponse = iPayService.iPayCharging(new IPayChargingRequest("12345", "375256257275", BigDecimal.ZERO));

        assertEquals(expectedResponse.getResultMap().get("fhCode"), actualResponse.getResultMap().get("fhCode"));
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(1)).findByCode(any());
    }

    @Test
    @DisplayName("Return fhCode for success first iPay charging")
    void iPayCharging_whenSuccessFirstCharging_thenReturnResponse() throws Exception {
        ChainResult chainResult = getIPayChargingSuccessChainResult();
        when(iPayOnlineTransactionRepository.getIPayFinTransactionByTransactionId(anyString()))
                .thenReturn(new ArrayList<>());
        when(chainService.executeChain(any(), any(), any())).thenReturn(chainResult);
        when(iPayOnlineTransactionRepository.save(any())).thenReturn(new IPayOnlineTransaction());
        ServiceResponseDto<Map<String, Object>> expectedResponse = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);

        ServiceResponseDto<Map<String, Object>> actualResponse = iPayService.iPayCharging(new IPayChargingRequest("12345", "375256257275", BigDecimal.ZERO));

        assertEquals(expectedResponse.getResultMap().get("fhCode"), actualResponse.getResultMap().get("fhCode"));
        verify(iPayOnlineTransactionRepository, times(1)).getIPayFinTransactionByTransactionId(anyString());
        verify(chainService, times(1)).executeChain(any(), any(), any());
        verify(iPayOnlineTransactionRepository, times(1)).save(any());
        verify(iPayOnlineTransactionRepository, times(0)).findByCode(any());
    }

    private ChainResult getIPayChargingSuccessChainResult() {
        ChainResult chainResult = new ChainResult();
        ChainResultElement chainResultElement = new ChainResultElement();
        chainResultElement.setName("fhCode");
        chainResultElement.setValue("123456");
        chainResult.setResultCode(ResultCodeType.SUCCESS);
        chainResult.setTransactionId("12345");
        chainResult.getResultList().add(chainResultElement);
        return chainResult;
    }

    private List<Tuple> getTupleListWithContractType(String contractType) {
        List<Tuple> accountInfoList = new ArrayList<>();
        MockTuple tuple = new MockTuple();
        tuple.set(AccountTablesParameters.CONTRACT_TYPE.getFullName(), contractType);
        accountInfoList.add(tuple);
        return accountInfoList;
    }

    private List<Tuple> getTupleListWithFIO(String firstName, String lastName, String midName) {
        List<Tuple> accountInfoList = new ArrayList<>();
        MockTuple tuple = new MockTuple();
        tuple.set(AccountTablesParameters.CONTRACT_TYPE.getFullName(), ContractType.IND.name());
        tuple.set(AccountTablesParameters.FIRST_NAME.getFullName(), firstName);
        tuple.set(AccountTablesParameters.LAST_NAME.getFullName(), lastName);
        tuple.set(AccountTablesParameters.MID_NAME.getFullName(), midName);
        accountInfoList.add(tuple);
        return accountInfoList;
    }
}