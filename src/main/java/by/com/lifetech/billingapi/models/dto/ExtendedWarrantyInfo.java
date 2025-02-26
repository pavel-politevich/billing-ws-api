package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedWarrantyInfo {
    @Schema(description = "sertificate ID", example = "893750306180538449")
    private Long id;
    private String state;
    private String deviceName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime nextPaymentDate;
    private int period;
}
