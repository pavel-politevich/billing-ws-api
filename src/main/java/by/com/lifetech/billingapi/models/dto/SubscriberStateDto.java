package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Subscriber billing state")
public class SubscriberStateDto {
	
	public SubscriberStateDto(String code) {
		super();
		this.code = code;
		this.name = "";
	}

	@Schema(description = "Subscriber state code", example = "ACT/STD/STD")
	private String code;

	@Schema(description = "Subscriber state name", example = "Active")
	private String name;
}