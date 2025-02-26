package by.com.lifetech.billingapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SaluteServiceOperation {
    ACT("salute_service_act")
    ,DEACT("salute_service_deact");

    private final String chainName;
}
