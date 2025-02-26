package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.InputParametersCheckingConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MsisdnChangeRequest {
    @Schema(description = "Mobile number for IND payments", example = "375256257001")
    @Length(min = InputParametersCheckingConfig.MSISDN_MIN_LENGTH, max = InputParametersCheckingConfig.MSISDN_MAX_LENGTH)
    private String msisdn;
    @Schema(description = "New MSISDN")
    @Length(min = InputParametersCheckingConfig.MSISDN_MIN_LENGTH, max = InputParametersCheckingConfig.MSISDN_MAX_LENGTH)
    private String newMsisdn;
    @Schema(description = "Number category from dictionary")
    @NotBlank
    private String msisdnCategory;
    @Schema(description = "Agent code", example = "62962618")
    private String agentCode;
    @Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;
    @Schema(description = "Agent login", example = "test@life.com.by")
    private String agent;
    @Schema(description = "SIM number - ICCID", example = "893750333331236223")
    @NotBlank
    @JsonProperty("simNo")
    private String simno;
    @Schema(description = "URL document")
    @JsonProperty("urlDocument")
    private String url_document;
}
