package by.com.lifetech.billingapi.models.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

import by.com.lifetech.billingapi.models.dto.cboss.GetInformationBalanceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Balance attributes")
public class BalanceInfDto {

	@Schema(description = "Balance code", example = "Line_Main")
	private String code;
	@Schema(description = "Balance name", example = "Основной счет")
	private String name;
	@Schema(description = "Balance index for sorting", example = "30")
	private int sort;
	@JsonIgnore
	private String groupCode;
	@Schema(description = "Measure unit code in billing", example = "Seconds")
	private String measureCode;
	private BigDecimal amount;
	private BigDecimal reserved;
	@Schema(description = "Rounded value with units", example = "2.89 руб")
	private String formattedValue;
	private List<BalancePocketDto> pockets;

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
		BalanceInfDto other = (BalanceInfDto) obj;
		return Objects.equals(code, other.code);
	}

	public BalanceInfDto(String code) {
		super();
		this.code = code;
	}

	public BalanceInfDto(GetInformationBalanceDto cbossBal) {
		super();
		this.code = cbossBal.getBalanceName();

		if (cbossBal.getStart() == null && cbossBal.getEnd() == null && cbossBal.getLabel().isBlank()) {
			this.amount = BigDecimal.valueOf(cbossBal.getValue());
			this.reserved = BigDecimal.valueOf(cbossBal.getReserved());
			this.pockets = new ArrayList<BalancePocketDto>();
		} else {
			BalancePocketDto pl = new BalancePocketDto();
			pl.setAmount(BigDecimal.valueOf(cbossBal.getValue()));
			pl.setReserved(BigDecimal.valueOf(cbossBal.getReserved()));
			pl.setStartDate(cbossBal.getStart());
			pl.setEndDate(cbossBal.getEnd());
			pl.setCode(cbossBal.getLabel());
			this.pockets = new ArrayList<BalancePocketDto>();
			this.getPockets().add(pl);
		}
	}

	public BigDecimal getAmount() {
		if (this.getPockets() != null && !this.getPockets().isEmpty()) {
			return this.getPockets().stream().map(BalancePocketDto::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return amount;
		}
	}

	public BigDecimal getReserved() {
		if (this.getPockets() != null) {
			return this.getPockets().stream().map(BalancePocketDto::getReserved).reduce(BigDecimal.ZERO, BigDecimal::add);
		} else {
			return reserved;
		}
	}
}
