package by.com.lifetech.billingapi.models.dto.cboss;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetInformationBalanceDto {

	private static final String CBOSS_FORMATTER = "yyyyMMddHHmmss";

	@JsonProperty("balance_name")
	String balanceName;
	
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
		this.label = label.replace("\"", "");
	}
	
	public void setStart(String start) {
		if (!start.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CBOSS_FORMATTER);
			this.start = LocalDateTime.parse(start, formatter);
		}
		else this.start = null;
	}
	
	public void setEnd(String end) {
		if (!end.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CBOSS_FORMATTER);
			this.end = LocalDateTime.parse(end, formatter);
		}
		else this.end = null;
	}
	
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

}
