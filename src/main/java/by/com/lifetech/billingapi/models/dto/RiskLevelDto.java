package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Level of risk for subscriber")
public class RiskLevelDto {
	@Schema(description = "Level risk code", example = "PUB_OF")
	private String code;

	@Schema(description = "Level risk name", example = "Public official")
	private String name;
}
