package by.com.lifetech.billingapi.models.dto.cboss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreEvent {
    private static final String CBOSS_FORMATTER = "yyyyMMddHHmmss";

     Integer reference;
     String id;
     Integer eventState;
     Integer eventType;
     LocalDateTime eventStart;
     LocalDateTime eventTimeout;
     Long eventDuration;
     String status;
     ReservationBalances Balances;

    @JsonProperty("reference")
    public Integer getReference() {
        return reference;
    }

    @JsonProperty("Reference")
    public void setReference(Integer reference) {
        this.reference = reference;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("ID")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("eventState")
    public Integer getEventState() {
        return eventState;
    }

    @JsonProperty("EventState")
    public void setEventState(Integer eventState) {
        this.eventState = eventState;
    }

    @JsonProperty("eventType")
    public Integer getEventType() {
        return eventType;
    }

    @JsonProperty("EventType")
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    @JsonProperty("eventStart")
    public LocalDateTime getEventStart() {
        return eventStart;
    }

    @JsonProperty("EventStart")
    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    @JsonProperty("eventTimeout")
    public LocalDateTime getEventTimeout() {
        return eventTimeout;
    }

    @JsonProperty("EventTimeout")
    public void setEventTimeout(LocalDateTime eventTimeout) {
        this.eventTimeout = eventTimeout;
    }

    @JsonProperty("eventDuration")
    public Long getEventDuration() {
        return eventDuration;
    }

    @JsonProperty("EventDuration")
    public void setEventDuration(Long eventDuration) {
        this.eventDuration = eventDuration;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("balances")
    @JsonUnwrapped
    public ReservationBalances getBalances() {
        return Balances;
    }

    @JsonProperty("Balances")
    public void setBalances(ReservationBalances balances) {
        Balances = balances;
    }

    @JsonProperty("EventTimeout")
    public void setEventTimeout(String eventTimeout) {
        if (!eventTimeout.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CBOSS_FORMATTER);
            this.eventTimeout = LocalDateTime.parse(eventTimeout, formatter);
        }
        else this.eventTimeout = null;
    }

    @JsonProperty("EventStart")
    public void setEventStart(String eventStart) {
        if (!eventStart.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CBOSS_FORMATTER);
            this.eventStart = LocalDateTime.parse(eventStart, formatter);
        }
        else this.eventStart = null;
    }
}
