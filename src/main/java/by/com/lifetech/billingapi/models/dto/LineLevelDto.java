package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Subscriber line level")
public class LineLevelDto {
	@Schema(description = "Line level code", example = "POST")
	private String code;

	@Schema(description = "Line level name", example = "Postpaid")
	private String name;

	public LineLevelDto(String code) {
		super();
		this.code = code;
		this.name = "";
	}

	
}
