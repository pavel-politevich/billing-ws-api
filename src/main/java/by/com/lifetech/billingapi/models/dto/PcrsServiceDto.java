package by.com.lifetech.billingapi.models.dto;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PCRF service")
public class PcrsServiceDto {

	@Schema(description = "PCRF service code", example = "Viber_RG_110")
	private String code;
	@Schema(description = "PCRF service name", example = "Internet traffic for the Viber resource")
	private String name;
	@Schema(description = "PCRF service value. 1 - active, 0- deactive", example = "1")
	private String value;
	@Schema(description = "PCRF attr index for sorting", example = "1")
	private int sort;
	
	@Override
	public int hashCode() {
		return Objects.hash(code);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PcrsServiceDto other = (PcrsServiceDto) obj;
		return Objects.equals(code, other.code);
	}
	
}
