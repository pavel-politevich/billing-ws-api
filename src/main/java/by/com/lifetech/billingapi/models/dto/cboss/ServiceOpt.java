package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceOpt {
	
	@JsonProperty("ServiceID")
	public String serviceID;
	@JsonProperty("Status")
	public String status;

}
