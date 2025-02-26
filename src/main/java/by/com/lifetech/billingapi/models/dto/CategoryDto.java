package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Marketing category (MID)")
public class CategoryDto {
	@Schema(description = "category code", example = "Infinite COMM")
	private String code;
	@Schema(description = "category name", example = "Infinite for 15,9 rub")
	private String name;
	
	public CategoryDto(String code) {
		super();
		this.code = code;
		this.name = "";
	}
}
