package by.com.lifetech.billingapi.models.requests;

import by.com.lifetech.billingapi.configurations.properties.inputchecking.MsisdnDefaultCheck;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationExistingRequest {
    @Schema(description = "Mobile number", example = "375256257415")
    @MsisdnDefaultCheck
    private String msisdn;
    @Schema(description = "Category", example = "6_XiaomiGroomingKitProXMGHT2KITLF_30.07.2024_1mdisc_82.50_1")
    @NotEmpty
    private String category;
    @Schema(description = "imei", example = "356445581833149")
    @NotEmpty
    private String imei;
    @Schema(description = "Tariff code", example = "INF")
    @NotEmpty
    private String tariffCode;
    @Schema(description = "Terminal Id", example = "6_XiaomiGroomingKitProXMGHT2KITLF_30.07.2024_1mdisc_82.50_1")
    @NotEmpty
    private String deviceType;
    @Schema(description = "Login of user", example = "8464381_1")
    private String agent;
    @Schema(description = "Point of sale", example = "8464381")
    private String pointOfSaleCode;
    @Schema(description = "Agreement", example = "/opt/iguana-uploads/agreements/IND_893750310158619094.pdf")
    private String url_document;
    @Schema(description = "Agent code", example = "62962618")
    private String representativeOfOperator;
    @Schema(description = "Sales ID", example = "")
    private String sid;
    @Schema(description = "Source from which subscriber got information about offer", example = "")
    private String informationSource;
    @Schema(description = "Public figure", example = "YES")
    private String public_official;
    @Schema(description = "Activity code", example = "1")
    private String occupation;
}
