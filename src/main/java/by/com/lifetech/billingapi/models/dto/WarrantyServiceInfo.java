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
public class WarrantyServiceInfo {
    private LocalDateTime repairStartDate;
    private LocalDateTime repairEndDate;
    private String repairState;
    private LocalDateTime replacementStartDate;
    private LocalDateTime replacementEndDate;
    private String replacementDeviceType;
    private ExtendedWarrantyInfo extendedWarrantyInfo;
}
