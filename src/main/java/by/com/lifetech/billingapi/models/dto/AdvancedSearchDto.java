package by.com.lifetech.billingapi.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdvancedSearchDto {
    @NotNull
    private Long accountId;
    private String msisdn;
    private String contractId;
    private LocalDateTime activationDate;
    private String firstName;
    private String lastName;
    private String middleName;
    private String companyName;
    private UniversalDictionary tariff;
    private UniversalDictionary contractType;
    private UniversalDictionary state;
}