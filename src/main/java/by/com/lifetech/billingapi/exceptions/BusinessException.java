package by.com.lifetech.billingapi.exceptions;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceBusinessError;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends Exception {
	@Serial
    private static final long serialVersionUID = 9106324539010112335L;
    private final ServiceBusinessError error;

    public BusinessException(String code, String message) {
        super(message);
        error = new ServiceBusinessError(code,message);
    }

    public BusinessException(BusinessErrorCode error) {
        this(error.name(), error.getMessage());
    }

    public BusinessException(ServiceBusinessError error) {
        super(error.getErrorMessage());
        this.error = error;
    }
}
