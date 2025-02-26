package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractAddressInfo {
    private boolean nonTypicalAddress;
    private String zipCode;
    private UniversalDictionary country;
    private UniversalDictionary region;
    private UniversalDictionary district;
    private UniversalDictionary cityType;
    private UniversalDictionary city;
    private UniversalDictionary streetType;
    private String streetName;
    private String buildingNo;
    private String housingNo;
    private String apartment;
}
