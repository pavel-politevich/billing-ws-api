package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransferPaymentRequest extends CancelPaymentRequest{
    @Schema(description = "contract code for payment transfer", example = "300134745")
    private String agreementNoB;

    @Schema(description = "msisdn for payment transfer", example = "375256257415")
    private String msisdnB;
}
