package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultChainServiceRequest {
	@Schema(description = "om code of product", example = "S_PRODUCT")
	@NotBlank
	private String productOfferingCode;

	@Schema(description = "mobile number", example = "375256257416")
	@NotBlank
	private String msisdn;
	
	@Schema(description = "channel code", example = "IUI")
	@NotBlank
	private String channel;
	
	@Schema(description = "agent code", example = "test.test@life.com.by")
	private String agent;
	
	@Schema(description = "code of sale point for dealer", example = "7777777")
	private String pointSaleCode;

	@Schema(description = "agent code of dealer", example = "83451")
	private String agentCode;
}
