package by.com.lifetech.billingapi.exceptions;

import by.com.lifetech.billingapi.models.enums.ServiceResultCode;

import java.io.Serial;

public class ExternalServiceException extends InternalException{
    @Serial
    private static final long serialVersionUID = 9106324539010112335L;

    public ExternalServiceException(String message) {
        super(message, ServiceResultCode.EXTERNAL_SERVICE_ERROR);
    }
}
