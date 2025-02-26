package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.models.dto.ManualPayment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManualPenaltyPaymentRequest extends ManualPayment {
    @Schema(description = "Mobile number for IND payments", example = "375256257001")
    @NotBlank
    private String msisdn;

    @Schema(description = "Bank code", example = "964")
    @NotBlank
    private String paymentAgentCode;

    @Schema(description = "FIO", example = "Иванов Иван")
    private String customerName;
}
