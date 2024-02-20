package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Subscriber contract type")
public class ContractTypeDto {
	@Schema(description = "Contract type code", example = "IND")
	private String code;

	@Schema(description = "Contract type name", example = "Individual Prepaid")
	private String name;

	public ContractTypeDto(String code) {
		super();
		this.code = code;
		this.name = "";
	}
	
	
}
