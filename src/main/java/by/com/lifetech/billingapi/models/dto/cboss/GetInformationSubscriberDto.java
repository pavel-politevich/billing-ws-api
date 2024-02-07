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
	String language_id;
	
	@JsonProperty("tariff_plan")
	String tariff_plan;
	
	@JsonProperty("state")
	String state;
	
	@JsonProperty("line_lvl")
	String line_lvl;
	
	@JsonProperty("use_common_main")
	String use_common_main;
	
	@JacksonXmlElementWrapper(useWrapping = false, localName = "balance")
	List<GetInformationBalanceDto> balance;
	
	@JsonProperty("account_id")
	String account_id;
	
	@JsonProperty("text")
	String text;
}
