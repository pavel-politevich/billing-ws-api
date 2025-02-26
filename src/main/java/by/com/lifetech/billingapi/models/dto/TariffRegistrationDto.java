package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Tariff plan for Registration")
public class TariffRegistrationDto extends TariffDto {
    @Schema(description = "TARIFF_TYPE_PLAN from product_attribute tbl", example = "PRE")
    private String tariffType;
}
