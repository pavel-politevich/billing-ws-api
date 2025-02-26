package by.com.lifetech.billingapi.models.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import by.com.lifetech.billingapi.models.entity.ProductOfferingInfo;
import by.com.lifetech.billingapi.models.enums.ProductState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Billing Product")
public class BillingProductDto {
	@Schema(description = "OM service code", example = "S_ROAMING_MIN_20_OTF")
	String code;
	@Schema(description = "OM service name", example = "20 минут в роуминге")
	String name;
	ProductState state;
	LocalDateTime dateFrom;
	LocalDateTime dateTo;
	@Schema(description = "service cost", example = "19,90")
	String serviceCost;
	@Schema(description = "service subgroup code", example = "BASIC_SERVICE_ROAMING")
	String subgroupCode;
	@Schema(description = "billing service type", example = "SERVICE")
	String productType;
	Boolean reactivation;
	Boolean deactivation;
	@JsonIgnore
	ProductOfferingInfo productInfo;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("BillingProductDto{");
		sb.append("code='").append(code).append('\'');
		if (state == ProductState.ACTIVE) {
			sb.append(", state=").append(state);
			sb.append(", dateFrom=").append(dateFrom);
			sb.append(", dateTo=").append(dateTo);
		}
		sb.append(", serviceCost='").append(serviceCost).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public void setDateFrom(String dateFrom) {
		if (dateFrom != null && !dateFrom.isBlank() && dateFrom.contains("+")) {
			this.dateFrom = LocalDateTime.parse(dateFrom.substring(0, dateFrom.indexOf("+")));
		}
	}
	public void setDateTo(String dateTo) {
		if (dateTo != null && !dateTo.isBlank() && dateTo.contains("+")) {
			this.dateTo = LocalDateTime.parse(dateTo.substring(0, dateTo.indexOf("+")));
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingProductDto other = (BillingProductDto) obj;
		return Objects.equals(code, other.code) && Objects.equals(productType, other.productType);
	}
	@Override
	public int hashCode() {
		return Objects.hash(code, productType);
	}
}
