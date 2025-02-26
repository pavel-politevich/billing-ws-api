package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NpmSubscriberMap {
	
	@JsonProperty("TYPE")
	public int type;
	@JsonProperty("MSISDN")
	public String msisdn;
	@JsonProperty("IMSI")
	public String imsi;

}
