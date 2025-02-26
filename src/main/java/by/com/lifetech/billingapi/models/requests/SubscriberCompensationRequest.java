package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class SubscriberCompensationRequest {
    @Schema(description = "Mobile number 12 digits", example = "375256257001")
    @MsisdnDefaultCheck
    private String msisdn;

    @Schema(description = "agent code", example = "test.test@life.com.by")
    private String agent;

    @Schema(description = "Amount of compensation", example = "0.99")
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal spent;
}
