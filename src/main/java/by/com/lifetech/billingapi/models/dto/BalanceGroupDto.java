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
@Schema(description = "Group of Balances")
public class BalanceGroupDto {

	@Schema(description = "Balance group code", example = "BALANCE_OR_CORPACC")
	private String groupCode;
	@Schema(description = "Balance group name", example = "Баланс или корпоративный счет")
	private String groupName;
	@Schema(description = "Balance group index for sorting", example = "100")
	private int sort;
	private List<BalanceInfDto> balances;

	@Override
	public int hashCode() {
		return Objects.hash(groupCode, groupName, sort);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceGroupDto other = (BalanceGroupDto) obj;
		return Objects.equals(groupCode, other.groupCode) && Objects.equals(groupName, other.groupName) && sort == other.sort;
	}

}
