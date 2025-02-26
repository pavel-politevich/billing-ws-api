package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentInformation {
    private String deviceId;
    private String deviceName;
    private String deviceType;
    private String imei;
    @JsonIgnore
    private UniversalDictionary sid;
    @JsonIgnore
    private UniversalDictionary methodOfSale;
    private String offerName;
    private LocalDateTime purchaseDate;

}
