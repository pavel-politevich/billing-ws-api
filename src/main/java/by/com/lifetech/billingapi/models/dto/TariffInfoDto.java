package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TariffInfoDto {
    LocalDateTime dateFrom;
    LocalDateTime dateTo;
    Double cost;
    String state;
    String tariffCode;
    String tariffName;
    String midCode;
    String midName;
}
