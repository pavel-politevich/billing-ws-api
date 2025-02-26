package by.com.lifetech.billingapi.utils;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.models.entity.dictionary.DictErrorMessage;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import by.com.lifetech.billingapi.models.repository.dictionary.DictErrorMessageRepository;
import by.com.lifetech.billingapi.wsdl.BusinessError;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.com.lifetech.billingapi.wsdl.ResultCodeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExceptionUtilsTest {
    @Mock
    private DictErrorMessageRepository errorMessageRepository;

    @InjectMocks
    private ExceptionUtils exceptionUtils;

    @Test
    @DisplayName("Get BusinessException by existed error code")
    void getBusinessExceptionByErrorCode_whenCodeExist_thenReturnErrorWithMessage() {
        BusinessException expectedBusinessException = new BusinessException("ERR40001", "MSISDN не найден");
        DictErrorMessage dictErrorMessage = new DictErrorMessage();
        dictErrorMessage.setCode("ERR40001");
        dictErrorMessage.setNameRu("MSISDN не найден");
        dictErrorMessage.setNameEn("MSISDN not found");
        when(errorMessageRepository.findByCode("ERR40001")).thenReturn(Optional.of(dictErrorMessage));

        BusinessException actualBusinessException = exceptionUtils.getBusinessExceptionByErrorCode("ERR40001");

        assertEquals(expectedBusinessException, actualBusinessException);
        verify(errorMessageRepository, times(1)).findByCode("ERR40001");
    }

    @Test
    @DisplayName("Get BusinessException by not existed error code")
    void getBusinessExceptionByErrorCode_whenCodeNotExist_thenReturnErrorWithEmptyMessage() {
        BusinessException expectedBusinessException = new BusinessException("ERR_TEST", "");
        when(errorMessageRepository.findByCode("ERR_TEST")).thenReturn(Optional.empty());

        BusinessException actualBusinessException = exceptionUtils.getBusinessExceptionByErrorCode("ERR_TEST");

        assertEquals(expectedBusinessException, actualBusinessException);
        verify(errorMessageRepository, times(1)).findByCode(anyString());
    }

    @Test
    @DisplayName("Get BusinessException by MSISDN_NOT_FOUND code")
    void getMsisdnNotFoundException_whenCalled_thenReturnErrorWithMsisdnNotFoundCode() {
        BusinessException expectedBusinessException = new BusinessException(BusinessErrorCode.MSISDN_NOT_FOUND.getCode(), "MSISDN не найден");
        DictErrorMessage dictErrorMessage = new DictErrorMessage();
        dictErrorMessage.setCode(BusinessErrorCode.MSISDN_NOT_FOUND.getCode());
        dictErrorMessage.setNameRu("MSISDN не найден");
        dictErrorMessage.setNameEn("MSISDN not found");
        when(errorMessageRepository.findByCode(BusinessErrorCode.MSISDN_NOT_FOUND.getCode())).thenReturn(Optional.of(dictErrorMessage));

        BusinessException actualBusinessException = exceptionUtils.getMsisdnNotFoundException();

        assertEquals(expectedBusinessException, actualBusinessException);
        verify(errorMessageRepository, times(1)).findByCode(BusinessErrorCode.MSISDN_NOT_FOUND.getCode());
    }

    @Test
    @DisplayName("Get BusinessException by businessError from ChainResult")
    void getBusinessErrorByChainResult_whenBusinessErrorNotNull_thenReturnBusinessErrorCodeAndMessage() {
        ChainResult chainResult = new ChainResult();
        BusinessError businessError = new BusinessError();
        businessError.setErrorMessage("Error message");
        businessError.setErrorName("ERR_TEST");
        chainResult.setBusinessError(businessError);
        BusinessException expectedBusinessException = new BusinessException("ERR_TEST", "Error message");

        BusinessException actualBusinessException = exceptionUtils.getBusinessErrorByChainResult(chainResult);

        assertEquals(expectedBusinessException, actualBusinessException);
    }

    @Test
    @DisplayName("Get BusinessException with empty fields from businessError with null fields")
    void getBusinessErrorByChainResult_whenFieldsOfBusinessErrorNull_thenReturnErrorWithEmptyFields() {
        ChainResult chainResult = new ChainResult();
        BusinessError businessError = new BusinessError();
        chainResult.setBusinessError(businessError);
        BusinessException expectedBusinessException = new BusinessException("", "");

        BusinessException actualBusinessException = exceptionUtils.getBusinessErrorByChainResult(chainResult);

        assertEquals(expectedBusinessException, actualBusinessException);
    }

    @Test
    @DisplayName("Get BusinessException with empty fields from ChainResult without businessError")
    void getBusinessErrorByChainResult_whenBusinessErrorNull_thenReturnErrorWithEmptyFields() {
        ChainResult chainResult = new ChainResult();
        BusinessException expectedBusinessException = new BusinessException("", "");

        BusinessException actualBusinessException = exceptionUtils.getBusinessErrorByChainResult(chainResult);

        assertEquals(expectedBusinessException, actualBusinessException);
    }

    @Test
    @DisplayName("Get ChainException by ChainResult fields with result description")
    void getChainErrorByChainResult_whenResultDescriptionNotNull_thenReturnErrorWithChainInfo() {
        ChainResult chainResult = new ChainResult();
        chainResult.setResultCode(ResultCodeType.CHAIN_UNKNOWN_ERROR);
        chainResult.setTransactionId("12345");
        chainResult.setResultDescription("Error description");
        ChainException expectedChainException = new ChainException("CHAIN_UNKNOWN_ERROR; transactionId: 12345", "Error description");

        ChainException actualChainException = exceptionUtils.getChainErrorByChainResult(chainResult);

        assertEquals(expectedChainException, actualChainException);
    }

    @Test
    @DisplayName("Get ChainException by ChainResult fields with result description")
    void getChainErrorByChainResult_whenResultDescriptionIsNull_thenReturnErrorWithEmptyChainInfo() {
        ChainResult chainResult = new ChainResult();
        chainResult.setResultCode(ResultCodeType.CHAIN_UNKNOWN_ERROR);
        chainResult.setTransactionId("12345");
        ChainException expectedChainException = new ChainException("CHAIN_UNKNOWN_ERROR; transactionId: 12345", "");

        ChainException actualChainException = exceptionUtils.getChainErrorByChainResult(chainResult);

        assertEquals(expectedChainException, actualChainException);
    }
}