package by.com.lifetech.billingapi.models.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PCRF attribute")
public class PcrfInfoDto {

	@Schema(description = "Mobile number", example = "375256257416")
	private String msisdn;
	
	@Schema(description = "IMSI", example = "257040120538449")
	private String imsi;
	
	private List<PcrsServiceDto> pcrfServices;
}
