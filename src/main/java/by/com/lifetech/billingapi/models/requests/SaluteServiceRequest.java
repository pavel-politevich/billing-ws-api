package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import by.com.lifetech.billingapi.models.enums.SaluteServiceOperation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaluteServiceRequest {
    @Schema(description = "Mobile number 12 digits", example = "375256257001")
    @MsisdnDefaultCheck
    private String msisdn;

    @Schema(description = "Profile ID for Salute service", example = "0")
    @NotBlank
    private String profileId;

    @Schema(description = "Type of operation")
    @NonNull
    private SaluteServiceOperation operation;
}