package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DealerPaymentRequest {
    @Schema(description = "Mobile number 12 digits", example = "375256257001")
    @NotBlank
    private String msisdn;

    @Schema(description = "FIO", example = "Иванов Иван")
    @NotBlank
    private String payerName;

    @Schema(description = "payment amount", example = "1.25")
    @NotNull
    private Double amount;

    @Schema(description = "agent code of dealer", example = "83451")
    private String agentCode;

    @Schema(description = "agent code", example = "test.test@life.com.by")
    private String agent;
}
