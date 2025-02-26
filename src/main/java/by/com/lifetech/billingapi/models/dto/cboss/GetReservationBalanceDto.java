package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetReservationBalanceDto {
    private static final String CBOSS_FORMATTER = "yyyyMMddHHmmss";

    String balanceName;
    String label;
    Double value;
    LocalDateTime start;
    LocalDateTime end;

    @JsonProperty("balanceName")
    public String getBalanceName() {
        return balanceName;
    }

    @JsonProperty("Balance_name")
    public void setBalanceName(String balanceName) {
        this.balanceName = balanceName;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("Label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(Double value) {
        this.value = value;
    }

    @JsonProperty("start")
    public LocalDateTime getStart() {
        return start;
    }

    @JsonProperty("end")
    public LocalDateTime getEnd() {
        return end;
    }

    @JsonProperty("Start")
    public void setStart(String start) {
        if (!start.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CBOSS_FORMATTER);
            this.start = LocalDateTime.parse(start, formatter);
        }
        else this.start = null;
    }

    @JsonProperty("End")
    public void setEnd(String end) {
        if (!end.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CBOSS_FORMATTER);
            this.end = LocalDateTime.parse(end, formatter);
        }
        else this.end = null;
    }

    @JsonProperty("Start")
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    @JsonProperty("End")
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
