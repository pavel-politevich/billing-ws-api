package by.com.lifetech.billingapi.exceptions;

import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import lombok.Getter;

import java.io.Serial;
import java.util.Objects;

@Getter
public class InternalException extends Exception{
    @Serial
    private static final long serialVersionUID = 9106324539010112335L;
    private ServiceResultCode resultCode;

    public InternalException(String message) {
        super(message);
        resultCode = ServiceResultCode.INTERNAL_ERROR;
    }

    protected InternalException(String message, ServiceResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalException that = (InternalException) o;
        return resultCode == that.resultCode && super.getMessage().equals(that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, super.getMessage());
    }
}
