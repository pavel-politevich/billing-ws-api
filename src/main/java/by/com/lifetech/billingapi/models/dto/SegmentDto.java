package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Subscriber's segment")
public class SegmentDto {
	
	@Schema(description = "Subscriber segment code", example = "VIP")
	private String code;

	@Schema(description = "Subscriber segment name", example = "VIP corporate")
	private String name;
	
	public SegmentDto(String code) {
		super();
		this.code = code;
		this.name = "";
	}

}
