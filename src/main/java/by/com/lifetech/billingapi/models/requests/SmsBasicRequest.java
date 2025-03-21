package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsBasicRequest {
    @Schema(description = "Sms code", example = "SMS_SERVICE_ACT")
    @NotBlank
    private String smsCode;

    @Schema(description = "Mobile number 12 digits", example = "375256257001", requiredMode = Schema.RequiredMode.REQUIRED)
    @MsisdnDefaultCheck
    private String msisdn;

    @Schema(description = "Map of sms parameters", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Map<String, String> params;
}