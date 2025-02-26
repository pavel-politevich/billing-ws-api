package by.com.lifetech.billingapi.models.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalancePocketDto {

	@Schema(description = "Pocket label code", example = "PL_YANDPLUS_MULTI")
	private String code;
	@Schema(description = "Pocket label name", example = "Яндекс Плюс")
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private BigDecimal amount;
	private BigDecimal reserved;
	@Schema(description = "Rounded value with units", example = "8.00 ГБ")
	private String formattedValue;
}
