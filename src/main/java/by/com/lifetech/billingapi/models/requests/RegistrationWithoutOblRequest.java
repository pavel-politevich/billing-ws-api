package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationWithoutOblRequest {
    @Schema(description = "Mobile number", example = "375256257415")
    @MsisdnDefaultCheck
    private String msisdn;
    @Schema(description = "imei", example = "356445581833149")
    @NotEmpty
    private String imei;
    @Schema(description = "Device Id", example = "0_Honor8X_28.05.2020_188.75_2")
    @JsonProperty("deviceCode")
    @NotEmpty
    private String device_code;
    @Schema(description = "Login of user", example = "8464381_1")
    private String agent;
    @Schema(description = "channel code", example = "lifeCRM")
    private String channel;
    @Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;
    @Schema(description = "Agreement", example = "/opt/iguana-uploads/agreements/IND_893750310158619094.pdf")
    @JsonProperty("urlDocument")
    private String url_document;
    @Schema(description = "Agent code", example = "62962618")
    private String representativeOfOperator;
    @Schema(description = "Sales ID", example = "")
    private String sid;
    @Schema(description = "Source from which subscriber got information about offer", example = "")
    private String informationSource;
    @Schema(description = "Public figure", example = "YES")
    @JsonProperty("publicOfficial")
    private String public_official;
    @Schema(description = "Activity code", example = "1")
    private String occupation;
}
