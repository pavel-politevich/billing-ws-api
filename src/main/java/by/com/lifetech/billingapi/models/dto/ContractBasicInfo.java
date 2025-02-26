package by.com.lifetech.billingapi.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractBasicInfo {
	private String accountId;
	private String contractId;
    private String contractType;
    private LocalDateTime createDate;
    private String firstName;
    private String lastName;
    private String middleName;
    private String companyName;
}
