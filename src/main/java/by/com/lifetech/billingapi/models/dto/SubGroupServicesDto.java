package by.com.lifetech.billingapi.models.dto;

import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubGroupServicesDto {

	private String subGroupCode;
	private String subGroupName;
	private int sort;
	private Set<BillingProductDto> services;
	
	public SubGroupServicesDto(String subGroupCode) {
		super();
		this.subGroupCode = subGroupCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(subGroupCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubGroupServicesDto other = (SubGroupServicesDto) obj;
		return Objects.equals(subGroupCode, other.subGroupCode);
	}
	
}
