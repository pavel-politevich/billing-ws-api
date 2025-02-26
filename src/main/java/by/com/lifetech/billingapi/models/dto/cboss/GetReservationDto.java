package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetReservationDto {
    @JacksonXmlElementWrapper(useWrapping = false, localName = "CreEvent")
    @JsonProperty(value = "CreEvent")
    public List<CreEvent> creEventList;
}
