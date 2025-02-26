package by.com.lifetech.billingapi.utils;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import by.com.lifetech.billingapi.models.repository.dictionary.DictErrorMessageRepository;
import by.com.lifetech.billingapi.wsdl.BusinessError;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExceptionUtils {
    private final DictErrorMessageRepository errorMessageRepository;

    public BusinessException getBusinessExceptionByErrorCode(String code) {
        String message;
        try {
            message = errorMessageRepository.findByCode(code).orElseThrow().getNameRu();
        } catch(Exception e) {
            message = "";
        }
        return new BusinessException(code, message);
    }

    public BusinessException getMsisdnNotFoundException() {
        return getBusinessExceptionByErrorCode(BusinessErrorCode.MSISDN_NOT_FOUND.getCode());
    }

    public BusinessException getBusinessErrorByChainResult(ChainResult chainResult) {
        String code = "";
        String message = "";
        BusinessError businessError = chainResult.getBusinessError();

        if (businessError != null) {
            code = Optional.ofNullable(businessError.getErrorName()).orElse(code);
            message = Optional.ofNullable(businessError.getErrorMessage()).orElse(message);
        }

        return new BusinessException(code, message);
    }

    public ChainException getChainErrorByChainResult(ChainResult chainResult) {
        StringBuilder message = new StringBuilder().append(chainResult.getResultCode().name())
                .append("; transactionId: ")
                .append(chainResult.getTransactionId().toString());
        return new ChainException(message.toString(), Optional.ofNullable(chainResult.getResultDescription()).orElse(""));
    }
}
