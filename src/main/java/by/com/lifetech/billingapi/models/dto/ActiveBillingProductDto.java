package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveBillingProductDto {
	
	@JsonProperty(value = "om_code")
	String code;
	@JsonProperty(value = "type")
	String type;
	@JsonProperty(value = "start_date")
	String dateFrom;
	@JsonProperty(value = "end_date")
	String dateTo;
	@JsonProperty(value = "cost")
	String cost;
	@JsonProperty(value = "state")
	String state;
}
