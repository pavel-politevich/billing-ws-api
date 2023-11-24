package by.com.lifetech.billingapi.models.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegHistoryRequestDto {
	
	@Schema(description = "language code", example = "RU")
	private String language;
	
	@Schema(description = "date format yyyy-mm-ddThh:mm:ss")
	private LocalDateTime dateTo;
	
	@Schema(description = "date format yyyy-mm-ddThh:mm:ss")
	private LocalDateTime dateFrom;
	
	@Schema(description = "contract code to search", example = " ")
	private String contractCode;
	
	@Schema(description = "agent code that activated the SIM card", example = "7777777_1")
	private String agent;
	
	@Schema(description = "point of sale code", example = "7777777")
	private String salesPoint;
	
	private TariffDto tariff;
	private CategoryDto category;
	private RegistrationTypeDto registrationType;

	
}
