package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

	@JsonProperty("ServiceID")
	public String serviceID;
	@JsonProperty("BundleID")
	public int bundleID;
	@JsonProperty("UsabilityStartDate")
	public String usabilityStartDate;
	@JsonProperty("UsabilityEndDate")
	public String usabilityEndDate;
	@JsonProperty("UsageState")
	public int usageState;
}
