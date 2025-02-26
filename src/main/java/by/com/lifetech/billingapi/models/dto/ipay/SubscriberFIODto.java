package by.com.lifetech.billingapi.models.dto.ipay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberFIODto {
    private String firstName;
    private String patronymic;
    private String familyName;
}
