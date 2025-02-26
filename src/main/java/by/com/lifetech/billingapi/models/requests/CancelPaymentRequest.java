package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.InputParametersCheckingConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CancelPaymentRequest {
    @Schema(description = "Mobile number 12 digits", example = "375256257001")
    @Length(min = InputParametersCheckingConfig.MSISDN_MIN_LENGTH, max = InputParametersCheckingConfig.MSISDN_MAX_LENGTH)
    private String msisdn;

    @Schema(description = "financial history code", example = "740704405491")
    @NotBlank
    private String financialHistoryCode;

    @Schema(description = "agent login", example = "test.test@life.com.by")
    @NotBlank
    private String channelUser;

    @Schema(description = "comment")
    private String comments;
}
