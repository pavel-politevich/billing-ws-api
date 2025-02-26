package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractIdentificationInfo {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDateTime birthDate;
    private String citizenship;
    private String documentType;
    private String identificationNumber;
    private String documentSeries;
    private String documentNumber;
    private String documentAuthority;
    private LocalDateTime documentIssuedDate;
    private LocalDateTime documentValidityDate;
}
