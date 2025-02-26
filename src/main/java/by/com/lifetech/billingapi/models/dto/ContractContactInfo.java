package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractContactInfo {
    private String contactPhone;
    private String additionalContactPhone;
    private String email;
    private String comments;
    private String fullAddress;
}
