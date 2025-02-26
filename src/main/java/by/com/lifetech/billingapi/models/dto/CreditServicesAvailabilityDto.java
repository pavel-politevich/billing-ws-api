package by.com.lifetech.billingapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditServicesAvailabilityDto {
    private CreditResultCheckDto creditLimit;
    private CreditResultCheckDto deferredTariff;
    private CreditResultCheckDto extraMoney;
    private CreditResultCheckDto postPaid;
}
