package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInformationDto {
	@Override
	public String toString() {
		return "GetInformationDto [subscriber=" + subscriber + "]";
	}

	@JsonProperty("subscriber")
	GetInformationSubscriberDto subscriber;

}
