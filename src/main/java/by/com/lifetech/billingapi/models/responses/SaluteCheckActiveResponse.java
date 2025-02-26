package by.com.lifetech.billingapi.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaluteCheckActiveResponse {
	@Schema(description = "subscription blocking state", example = "0")
	private int status;
	@Schema(description = "has active subscription or not")
	private boolean active;

}
