package by.com.lifetech.billingapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IPayOperationResponse {
    CHARGING_SUCCESS("2"),
    MONEY_BACK_SUCCESS("0"),
    MONEY_BACK_ERROR("1");

    private String code;
}
