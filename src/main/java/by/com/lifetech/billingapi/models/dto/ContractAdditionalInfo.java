package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractAdditionalInfo {
    private boolean publicOfficial;
    private UniversalDictionary occupation;
    private UniversalDictionary sourceInfo;
}
