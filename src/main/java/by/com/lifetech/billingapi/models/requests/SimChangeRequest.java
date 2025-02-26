package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.InputParametersCheckingConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SimChangeRequest {
    @Schema(description = "Mobile number for IND payments", example = "375256257001")
    @Length(min = InputParametersCheckingConfig.MSISDN_MIN_LENGTH, max = InputParametersCheckingConfig.MSISDN_MAX_LENGTH)
    private String msisdn;
    @Schema(description = "Dealer code")
    private String dealerCode;
    @Schema(description = "Agent code", example = "62962618")
    private String agentCode;
    @Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;
    @Schema(description = "Agent login", example = "test@life.com.by")
    private String agent;
    @Schema(description = "SIM number - ICCID", example = "893750333331236223")
    @NotBlank
    @JsonProperty(value = "simNo")
    private String simno;
    @Schema(description = "Type of sim change", example = "PAID")
    @NotBlank
    private String type;

    @Hidden
    @JsonProperty(value = "simno")
    public @NotBlank String getSimno() {
        return simno;
    }

    @JsonProperty(value = "simNo")
    public void setSimno(@NotBlank String simno) {
        this.simno = simno;
    }
}
