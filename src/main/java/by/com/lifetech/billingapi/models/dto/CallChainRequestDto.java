package by.com.lifetech.billingapi.models.dto;

import java.util.Map;

import by.com.lifetech.billingapi.models.enums.ChainType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallChainRequestDto {

	@Schema(description = "chain name", example = "ussd105new_tp_info")
	private String chainName;

	@Schema(description = "chain type: OM, CIM, FM", example = "OM")
	private ChainType chainType;

	@Schema(description = "Map of input parameters")
	private Map<String, Object> mapParams;

}
