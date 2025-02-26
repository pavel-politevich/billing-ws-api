package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDetails {
    private ContractBasicInfo basicInfo;
    private ContractAddressInfo addressInfo;
    private ContractContactInfo contactInfo;
    private ContractIdentificationInfo identificationInfo;
    private ContractAdditionalInfo contractAdditionalInfo;
}
