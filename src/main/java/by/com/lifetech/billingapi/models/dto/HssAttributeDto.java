package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Hss attribute")
public class HssAttributeDto {
	
	@Schema(description = "Hss attribute code", example = "IMSI")
	private String code;
	@Schema(description = "Hss attribute name", example = "International mobile subscriber Identity")
	private String name;
	@Schema(description = "Hss attribute value", example = "257040120538449")
	private String value;
	@Schema(description = "Hss attr index for sorting", example = "1")
	private int sort;

}
