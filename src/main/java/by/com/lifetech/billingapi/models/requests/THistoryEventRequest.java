package by.com.lifetech.billingapi.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class THistoryEventRequest {

	@Schema(description = "mobile_no to search", example = "375256257416")
	private String msisdn;
	
	@Schema(description = "contract id", example = "300134745")
	private String contractCode;
	
	@Schema(description = "transaction type code", example = "107101")
	private Long thcode;
	
	@Schema(description = "transaction comment", example = "Комментарий")
	private String comment;
	
	@Schema(description = "email or agent code", example = "test@life.com.by")
	private String agent;
}
