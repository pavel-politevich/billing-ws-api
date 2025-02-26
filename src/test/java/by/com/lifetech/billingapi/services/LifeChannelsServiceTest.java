package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.AccountTablesParameters;
import by.com.lifetech.billingapi.models.enums.CommonCheckAnswer;
import by.com.lifetech.billingapi.models.repository.TransactionHistoryRepository;
import by.com.lifetech.billingapi.models.repository.account.AccountExTblRepository;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import by.com.lifetech.billingapi.utils.test.MockTuple;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LifeChannelsServiceTest {
    @Mock
    private AccountExTblRepository accountExTbl;
    @Mock
    private ExceptionUtils exceptionUtils;
    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @InjectMocks
    private LifeChannelsService lifeChannelsService;

    @Test
    @DisplayName("Get Yes for two msisdn with common owner")
    void checkCommonOwner_whenCommonOwner_thenReturnYes() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithPassportType("8161909C096HB8", "PASSPORT_RB"));
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(CommonCheckAnswer.YES);

        ServiceResponseDto<CommonCheckAnswer> actualResponse = lifeChannelsService.checkCommonOwner("375256257275", "375256257270");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(2)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Get No for two msisdn with different owners")
    void checkCommonOwner_whenDifferentOwners_thenReturnNo() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithPassportType("8161909C096HB8", "PASSPORT_RB"))
                .thenReturn(new ArrayList<>());
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(CommonCheckAnswer.NO);

        ServiceResponseDto<CommonCheckAnswer> actualResponse = lifeChannelsService.checkCommonOwner("375256257275", "375256257270");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(2)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Get No for not found first msisdn")
    void checkCommonOwner_whenFirstMsisdnNotFound_thenReturnNo() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any())).thenReturn(new ArrayList<>());
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(CommonCheckAnswer.NO);

        ServiceResponseDto<CommonCheckAnswer> actualResponse = lifeChannelsService.checkCommonOwner("375256257275", "375256257270");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Get No for non resident passport type of first msisdn")
    void checkCommonOwner_whenFirstMsisdnPassportTypeCheckFail_thenReturnYNo() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithPassportType("8161909C096HB8", "PASSPORT_RU"));
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(CommonCheckAnswer.NO);

        ServiceResponseDto<CommonCheckAnswer> actualResponse = lifeChannelsService.checkCommonOwner("375256257275", "375256257270");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Get No for non resident passport type of second msisdn")
    void checkCommonOwner_whenSecondMsisdnPassportTypeCheckFail_thenReturnYNo() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(),any()))
                .thenReturn(getTupleListWithPassportType("8161909C096HB8", "PASSPORT_RB"))
                .thenReturn(getTupleListWithPassportType("8161909C096HB8", "PASSPORT_RU"));
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(CommonCheckAnswer.NO);

        ServiceResponseDto<CommonCheckAnswer> actualResponse = lifeChannelsService.checkCommonOwner("375256257275", "375256257270");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(2)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Get response for exist service activation for check earlier activation")
    void checkIsServiceWasActEarlier_whenServiceActivationFound_thenReturnYes() throws Exception {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(), any())).thenReturn(getTupleListWithContractId());
        when(transactionHistoryRepository.findServiceAnyActivation("375256257275", "300159535", "S_LITRES_MONTHLY"))
                .thenReturn(Optional.of(12345L));
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = getCheckIsServiceWasActEarlierResponse(CommonCheckAnswer.YES);

        ServiceResponseDto<CommonCheckAnswer> actualResponse = lifeChannelsService.checkIsServiceWasActEarlier("375256257275", "S_LITRES_MONTHLY");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
        verify(transactionHistoryRepository, times(1)).findServiceAnyActivation(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Get response for not exist service activation for check earlier activation")
    void checkIsServiceWasActEarlier_whenServiceActivationNotFound_thenReturnNo() throws Exception {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(), any())).thenReturn(getTupleListWithContractId());
        when(transactionHistoryRepository.findServiceAnyActivation("375256257275", "300159535", "S_LITRES_MONTHLY"))
                .thenReturn(Optional.empty());
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = getCheckIsServiceWasActEarlierResponse(CommonCheckAnswer.NO);

        ServiceResponseDto<CommonCheckAnswer> actualResponse = lifeChannelsService.checkIsServiceWasActEarlier("375256257275", "S_LITRES_MONTHLY");

        assertEquals(expectedResponse, actualResponse);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
        verify(transactionHistoryRepository, times(1)).findServiceAnyActivation(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Throw exception for not found msisdn for check earlier activation")
    void checkIsServiceWasActEarlier_whenActiveMsisdnNotFound_thenThrowException() {
        when(accountExTbl.findAccountInfoByConditionsTuple(any(), any())).thenReturn(new ArrayList<>());
        BusinessException expectedBusinessException = new BusinessException("ERR40001", "MSISDN не найден");
        when(exceptionUtils.getMsisdnNotFoundException()).thenReturn(expectedBusinessException);

        BusinessException actualBusinessException = assertThrows(BusinessException.class, () -> {
            lifeChannelsService.checkIsServiceWasActEarlier("375256257275", "S_LITRES_MONTHLY");
        });

        assertEquals(expectedBusinessException, actualBusinessException);
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Throw exception for incorrect contract records count for check earlier activation")
    void checkIsServiceWasActEarlier_whenFoundMoreThenOneContract_thenThrowException() {
        List<Tuple> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        when(accountExTbl.findAccountInfoByConditionsTuple(any(), any())).thenReturn(list);

        assertThrows(InternalException.class, () -> {
            lifeChannelsService.checkIsServiceWasActEarlier("375256257275", "S_LITRES_MONTHLY");
        });
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    @Test
    @DisplayName("Throw exception for null contractId for check earlier activation")
    void checkIsServiceWasActEarlier_whenContractIdIsNull_thenThrowException() {
        List<Tuple> accountInfoList = new ArrayList<>();
        MockTuple tuple = new MockTuple();
        tuple.set(AccountTablesParameters.CONTRACT_ID.getFullName(), null);
        accountInfoList.add(tuple);
        when(accountExTbl.findAccountInfoByConditionsTuple(any(), any())).thenReturn(accountInfoList);

        NoSuchElementException  actualException = assertThrows(NoSuchElementException.class, () -> {
            lifeChannelsService.checkIsServiceWasActEarlier("375256257275", "S_LITRES_MONTHLY");
        });
        verify(accountExTbl, times(1)).findAccountInfoByConditionsTuple(any(), any());
    }

    private List<Tuple> getTupleListWithContractId() {
        List<Tuple> accountInfoList = new ArrayList<>();
        MockTuple tuple = new MockTuple();
        tuple.set(AccountTablesParameters.CONTRACT_ID.getFullName(), "300159535");
        accountInfoList.add(tuple);
        return accountInfoList;
    }

    private List<Tuple> getTupleListWithPassportType(String identificationCode, String passportType) {
        List<Tuple> accountInfoList = new ArrayList<>();
        MockTuple tuple = new MockTuple();
        tuple.set(AccountTablesParameters.PASSPORT_TYPE.getFullName(), passportType);
        tuple.set(AccountTablesParameters.IDENTIFICATION_CODE.getFullName(), identificationCode);
        accountInfoList.add(tuple);
        return accountInfoList;
    }

    private ServiceResponseDto<CommonCheckAnswer> getCheckIsServiceWasActEarlierResponse(CommonCheckAnswer answer) {
        ServiceResponseDto<CommonCheckAnswer> expectedResponse = new ServiceResponseDto<CommonCheckAnswer>().setDefaultSuccessResponse();
        expectedResponse.setResultMap(answer);
        return expectedResponse;
    }
}