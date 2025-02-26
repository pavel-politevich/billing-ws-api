package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPayChargingRequest {
    @Schema(description = "Transaction id of iPay operation", example = "7005")
    @NotBlank
    private String operID;

    @Schema(description = "Mobile number 12 digits", example = "375256257001")
    @MsisdnDefaultCheck
    private String msisdn;

    @Schema(description = "Amount of charging", example = "0.99")
    @NotNull
    private BigDecimal amount;
}
