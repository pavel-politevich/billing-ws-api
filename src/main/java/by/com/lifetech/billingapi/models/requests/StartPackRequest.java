package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StartPackRequest {
    @Schema(description = "Method of Sale", example = "EQUIPMENT_IN_INSTALLMENTS_FROM_THE_BANK_6")
    @NotEmpty
    private String methodOfSale;
    @Schema(description = "subscriber type INDIVIDUAL or LEGAL", example = "INDIVIDUAL")
    @NotEmpty
    private String customerType;
    @Schema(description = "is non-resident?", example = "false")
    @NotNull
    private boolean nonresident;
    @Schema(description = "subscriber age", example = "20")
    @NotNull
    private int age;
}
