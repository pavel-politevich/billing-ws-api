package by.com.lifetech.billingapi.exceptions;

import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper=true)
public class ChainException extends InternalException{
    @Serial
    private static final long serialVersionUID = 9106324539010112335L;
    private String chainInfo;

    public ChainException(String message, String chainInfo) {
        super(message, ServiceResultCode.CHAIN_ERROR);
        this.chainInfo = chainInfo;
    }
}
