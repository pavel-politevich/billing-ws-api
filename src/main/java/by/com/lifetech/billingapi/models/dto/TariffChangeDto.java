package by.com.lifetech.billingapi.models.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Tariff plan to change")
public class TariffChangeDto extends TariffDto implements Serializable {
	@Serial
	private static final long serialVersionUID = -6729834455435145515L;

	@Schema(description = "Tariff cost", example = "19.99")
	private double tariffCost;
	
	@Schema(description = "Tariff change fee", example = "4.50")
	private double tariffChangeFee;
	
	@Schema(description = "Marketing Id for special service", example = "Voice CHANGE 6m disc 11.9rub")
	private String mid;
	
	@Schema(description = "Web attributes for tariff")
	private Map<String, String> tariffInfo;
	
	@JsonIgnore
	private String serviceCode;
}
