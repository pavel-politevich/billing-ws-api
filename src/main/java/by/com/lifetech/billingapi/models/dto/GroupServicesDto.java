package by.com.lifetech.billingapi.models.dto;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupServicesDto {
	private int groupCode;
	private String groupName;
	private int sort;
	private List<SubGroupServicesDto> subgroups;
	
	@Override
	public int hashCode() {
		return Objects.hash(groupCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupServicesDto other = (GroupServicesDto) obj;
		return groupCode == other.groupCode;
	}
	
	public GroupServicesDto(int groupCode) {
		super();
		this.groupCode = groupCode;
	}
	
}
