package by.com.lifetech.billingapi.models.dto.cboss;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInformationSubscriberDto {

	@JsonProperty("language_id")
	String languageId;
	
	@JsonProperty("tariff_plan")
	String tariffPlan;
	
	@JsonProperty("state")
	String state;
	
	@JsonProperty("line_lvl")
	String lineLevel;
	
	@JsonProperty("use_common_main")
	String useCommonMain;
	
	@JacksonXmlElementWrapper(useWrapping = false, localName = "balance")
	@JsonProperty("balance")
	List<GetInformationBalanceDto> balances;
	
	@JsonProperty("account_id")
	String accountId;
	
	@JsonProperty("text")
	String text;

	@JsonProperty("MSISDN")
	String msisdn;
	
	public String getTariffPlan() {
		if (tariffPlan != null) {
			return tariffPlan.substring(tariffPlan.indexOf("_") + 1);
		} else {
			return tariffPlan;
		}
	}
}
