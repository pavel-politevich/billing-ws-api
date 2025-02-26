package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationBalances {
    @JacksonXmlElementWrapper(useWrapping = false, localName = "Balance")
    @JacksonXmlProperty(localName = "Balance")
    @JsonProperty("balanceList")
    List<GetReservationBalanceDto> balances;
}
