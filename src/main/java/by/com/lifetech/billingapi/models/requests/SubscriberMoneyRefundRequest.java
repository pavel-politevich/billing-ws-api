package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.InputParametersCheckingConfig;
import by.com.lifetech.billingapi.models.enums.RefundMoneyReason;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class SubscriberMoneyRefundRequest {
    @NotBlank
    private RefundMoneyReason refundReason;

    @Schema(description = "Mobile number 12 digits", example = "375256257001")
    @Length(min = InputParametersCheckingConfig.MSISDN_MIN_LENGTH, max = InputParametersCheckingConfig.MSISDN_MAX_LENGTH)
    private String msisdn;

    @Schema(description = "agent login", example = "test.test@life.com.by")
    @NotBlank
    private String channelUser;

    @Schema(description = "contract code for money refund", example = "000000333")
    @Hidden
    private String agreementNoB;

    @Schema(description = "msisdn for money refund", example = "375256257415")
    private String msisdnB;
}
