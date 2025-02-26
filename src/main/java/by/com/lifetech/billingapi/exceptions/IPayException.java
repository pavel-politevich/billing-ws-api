package by.com.lifetech.billingapi.exceptions;

import by.com.lifetech.billingapi.models.enums.IPayErrorCode;
import lombok.Getter;

import java.io.Serial;
import java.util.Objects;

@Getter
public class IPayException  extends Exception {
    @Serial
    private static final long serialVersionUID = 9106324539010112335L;
    private IPayErrorCode iPayErrorCode;

    public IPayException(String message, String businessErrorCode) {
        super(message);
        iPayErrorCode = IPayErrorCode.getErrorByBusinessCode(businessErrorCode);
        iPayErrorCode = iPayErrorCode != null ? iPayErrorCode : IPayErrorCode.NONRECOVERABLE_ERROR;
    }

    public IPayException(IPayErrorCode iPayErrorCode) {
        super(iPayErrorCode.getMessage());
        this.iPayErrorCode = iPayErrorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPayException that = (IPayException) o;
        return iPayErrorCode == that.iPayErrorCode && super.getMessage().equals(that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(iPayErrorCode, super.getMessage());
    }
}
