package by.com.lifetech.billingapi.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutopayServiceRequest {
    @Schema(description = "mobile number", example = "375256257416")
    @NotBlank
    private String msisdn;

    @Schema(description = "channel code", example = "MOB")
    @NotBlank
    private String channel;

    @Schema(description = "main mobile number, required for recipient", example = "375256257416")
    private String mainMsisdn;
}