package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartPackOpenedInfo {
    @Schema(description = "Method of Sale", example = "EQUIPMENT_IN_INSTALLMENTS_FROM_THE_BANK_6")
    private String methodOfSale;

    @Schema(description = "Code of tariff plan", example = "INF")
    private String tariffCode;

    @Schema(description = "subscriber type INDIVIDUAL or LEGAL", example = "INDIVIDUAL")
    private String customerType;

    @Schema(description = "subscriber age", example = "20")
    private int age;

    @Schema(description = "is non-resident?", example = "false")
    private boolean nonresident;
}