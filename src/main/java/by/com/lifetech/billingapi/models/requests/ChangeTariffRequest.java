package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTariffRequest {

	@Schema(description = "Tariff OM code", example = "INF")
	@NotBlank
	private String tariffCode;
	
	@Schema(description = "channel code", example = "MOB")
	@NotBlank
	private String channel;
	
	@Schema(description = "agent code", example = "test.test@life.com.by")
	private String agent;
	
	@Schema(description = "marketing id. For special change", example = "Voice CHANGE 6m disc 11.9rub")
	private String mid;

	private String msisdn;
}
