package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInfoFullDto {
	
	@JsonProperty("CreAccount")
	public CreAccount creAccount;
	@JsonProperty("NpmSubscriberMap")
	public NpmSubscriberMap npmSubscriberMap;
	@JsonProperty("CreBalance")
	public CreBalance creBalance;
	@JsonProperty("CreServiceOption")
	public CreServiceOption creServiceOption;
	@JsonProperty("CreServiceOptionStatus")
	public CreServiceOptionStatus creServiceOptionStatus;
	@JsonProperty("CreTrigger")
	public CreTrigger creTrigger;

}
