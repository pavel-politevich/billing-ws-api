package by.com.lifetech.billingapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BusinessErrorCode {
    ENTITY_NOT_FOUND("Required entity not found", "")
    ,SUBSCRIBER_NOT_FOUND("Subscriber not found", "")
    ,CONTRACT_NOT_FOUND("Contract not found", "")
    ,INCORRECT_RECORDS_COUNT("Incorrect records count in response", "")
    ,MSISDN_NOT_FOUND("MSISDN not found", "ERR40001");

    private String message;
    private String code;
}
