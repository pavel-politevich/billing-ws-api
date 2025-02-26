package by.com.lifetech.billingapi.models.requests;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TVplusPurchaseRequest {

	@Schema(description = "Mobile number 12 digits", example = "375256257001")
	@NotBlank
	private String msisdn;
	
	@Schema(description = "Transaction Type Code", example = "400900")
	@NotBlank
	private String otp_type;
	
	@Schema(description = "Type of One Time Purchase", example = "0.99")
	@NotNull
	private BigDecimal amount;

	@Schema(description = "Transaction code from partner", example = "123456789455")
	@NotBlank
	private String transactionCode;
	
	@Schema(description = "Comment for purchase", example = "Фильм 'Номер один'")
	private String comment;

}
