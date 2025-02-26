package by.com.lifetech.billingapi.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchSubscriberDto {
    @Schema(description = "Mobile No", example = "375255556677")
    String msisdn;
    @Schema(description = "Contract Id", example = "300134745")
    String contractId;
    SubscriberStateDto state;
    LocalDateTime activationDate;
    String firstName;
    String lastName;
    String middleName;
    String companyName;
    String contractType;
}
