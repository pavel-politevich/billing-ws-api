package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "SIM card registration type")
public class RegistrationTypeDto {

	@Schema(description = "registration type code", example = "INDIVIDUAL_REGISTRATION_EMPTY_SIM")
	private String code;
	@Schema(description = "registration type name", example = "Individual subscriber registration on empty SIM")
	private String name;
}
