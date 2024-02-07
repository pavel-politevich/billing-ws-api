package by.com.lifetech.billingapi.models.dto;

import java.util.Map;

import by.com.lifetech.billingapi.models.enums.PofileIdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOperationsRequestDto {
	
	@Schema(description = "Order / reorder / refuse service", example = "ORDER_WITHOUT_SEGMENT")
	private PofileIdType operation;

	@Schema(description = "OM product offering code", example = "S_SMS_100")
	private String code;
	
	@Schema(description = "Channel code", example = "MOB")
	private String channel;
	
	@Schema(description = "Map of search parameters. 'MSISDN' is required!")
	private Map<String, String> mapParams;

}
