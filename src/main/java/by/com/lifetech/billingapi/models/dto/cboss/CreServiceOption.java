package by.com.lifetech.billingapi.models.dto.cboss;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreServiceOption {

	@JacksonXmlElementWrapper(useWrapping = false, localName = "Service")
	@JsonProperty("Service")
	public List<Service> services;
}
