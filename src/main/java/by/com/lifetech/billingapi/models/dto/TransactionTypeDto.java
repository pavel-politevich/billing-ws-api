package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeDto {
	
	@Schema(description = "transaction type code", example = "200010")
	private Long code;
	
	@Schema(description = "transaction type name", example = "Сервис \"Перевод Баланса\"")
	private String name;

}
