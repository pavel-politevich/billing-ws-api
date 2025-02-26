package by.com.lifetech.billingapi.models.dto;

import by.com.lifetech.billingapi.models.entity.AssetType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Marketing id for Registration")
public class CategoryRegistrationDto extends CategoryDto {
    @Schema(description = "MID cost", example = "19.99")
    private Double cost;
    @Schema(description = "Available for NON-resident", example = "false")
    private boolean nonResident;
    @Schema(description = "Minimal Age", example = "14")
    private int age;
    private AssetType device;
    @Schema(description = "Web attributes for MID")
    private Map<String, String> categoryInfo;
}
