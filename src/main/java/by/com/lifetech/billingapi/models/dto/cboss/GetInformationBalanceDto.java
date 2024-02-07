package by.com.lifetech.billingapi.models.dto.cboss;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInformationBalanceDto {

	@JsonProperty("balance_name")
	String balance_name;
	
	@JsonProperty("label")
	String label;
	
	@JsonProperty("value")
	Double value;
	
	@JsonProperty("reserved")
	Double reserved;
	
	@JsonProperty("start")
	LocalDateTime start;
	
	@JsonProperty("end")
	LocalDateTime end;
	
	public void setLabel(String label) {
		this.label = label.replaceAll("\"", "");
	}
	
	public void setStart(String start) {
		if (!start.equals("")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			this.start = LocalDateTime.parse(start, formatter);
		}
		else this.start = null;
	}
	
	public void setEnd(String end) {
		if (!end.equals("")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			this.end = LocalDateTime.parse(end, formatter);
		}
		else this.end = null;
	}

}
