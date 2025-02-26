package by.com.lifetech.billingapi.models.dto;

import by.com.lifetech.billingapi.models.enums.HubActivationResult;
import by.com.lifetech.billingapi.models.enums.MVDResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Result of subscriber online registration")
public class OnlineRegistrationDto {
    @Schema(description = "Subscriber activation result")
    private HubActivationResult activationResult;

    @Schema(description = "Mobile number", example = "375256257416")
    private String msisdn;

    @Schema(description = "MVD check result")
    private MVDResult checkMIA;

    @Schema(description = "Business error code")
    private String checkReason;

    @Schema(description = "Contract number", example = "300162935")
    private String contractId;

    public OnlineRegistrationDto setStartValues() {
        activationResult = HubActivationResult.FAILED;
        msisdn = "";
        checkMIA = MVDResult.WITHOUT;
        checkReason = "";
        contractId = "";
        return this;
    }
}
