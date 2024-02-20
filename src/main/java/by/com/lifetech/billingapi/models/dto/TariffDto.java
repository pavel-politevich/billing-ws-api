package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User's tariff plan")
public class TariffDto {

	@Schema(description = "Tariff code from billing", example = "INF")
	private String code;

	@Schema(description = "Tariff name", example = "Infinite")
	private String name;

	public TariffDto(String code) {
		super();
		this.code = code;
		this.name = "";
	}

}
