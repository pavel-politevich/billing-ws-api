package by.com.lifetech.billingapi.models.requests;

import java.util.Map;

import by.com.lifetech.billingapi.models.enums.PofileIdType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOperationsRequest {
	
	@Schema(description = "Order / reorder / refuse service", example = "ORDER_WITHOUT_SEGMENT")
	@NotNull
	private PofileIdType operation;

	@Schema(description = "OM product offering code", example = "S_SMS_100")
	@NotBlank
	private String code;
	
	@Schema(description = "Channel code", example = "MOB")
	@NotBlank
	private String channel;
	
	@Schema(description = "Map of search parameters. 'MSISDN' is required!")
	private Map<String, String> mapParams;

}
