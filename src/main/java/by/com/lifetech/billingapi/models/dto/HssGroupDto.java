package by.com.lifetech.billingapi.models.dto;

import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Group of HSS attributes")
public class HssGroupDto {
	
	@Schema(description = "Hss group attribute code", example = "basic_info")
	private String code;
	@Schema(description = "Hss group attribute name", example = "Общая информация")
	private String name;
	@Schema(description = "Hss group index for sorting", example = "1")
	private int sort;
	private List<HssAttributeDto> attrList;
	
	@Override
	public int hashCode() {
		return Objects.hash(code, name, sort);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HssGroupDto other = (HssGroupDto) obj;
		return Objects.equals(code, other.code) && Objects.equals(name, other.name) && sort == other.sort;
	}

}
