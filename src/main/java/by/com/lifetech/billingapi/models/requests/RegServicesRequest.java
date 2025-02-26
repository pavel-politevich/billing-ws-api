package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegServicesRequest {
    @Schema(description = "Marketing id (category)", example = "12_RealmePadMini4GB-64GBWi-Fi_bankcredit_18.06.2024_462.50_0_1")
    @NotEmpty
    private String mid;

    @Schema(description = "Marketing id (category)", example = "12_RealmePadMini4GB-64GBWi-Fi_bankcredit_18.06.2024_462.50_0_1")
    private String tid;

    @Schema(description = "MTariff code", example = "CRTBL")
    @NotEmpty
    private String tariffCode;

    @Schema(description = "Point of sale", example = "8464381")
    @NotEmpty
    private String pointOfSaleCode;
}
