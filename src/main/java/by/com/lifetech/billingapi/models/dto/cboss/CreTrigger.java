package by.com.lifetech.billingapi.models.dto.cboss;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreTrigger {
	
	@JacksonXmlElementWrapper(useWrapping = false, localName = "RopInfo")
	@JsonProperty("RopInfo")
	public List<RopInfo> ropInfos;
}
