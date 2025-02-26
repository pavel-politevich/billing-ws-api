package by.com.lifetech.billingapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum IPayErrorCode {
    ERR40005("ERR40005", "522", "Money not enough"),
    ERR40001("ERR40001", "801", "Contract not found"),
    ERR40002("ERR40002", "801", "Contract not found"),
    ERR_TP_000("ERR-TP-000", "524", "Wrong customer type"),
    FIO_NOT_FOUND("FIO_NOT_FOUND", "4", "FIO not found"),
    GET_SERV_INFO_ERROR("GET_SERV_INFO_ERROR","1", "Check subscriber error"),
    OPERATION_ALREADY_PERFORMED("OPERATION_ALREADY_PERFORMED", "910", "Operation already success performed"),
    NONRECOVERABLE_ERROR("NONRECOVERABLE_ERROR", "3", "Nonrecoverable error");

    private String businessErrorCode;
    private String iPayErrorCode;
    private String message;

    public static IPayErrorCode getErrorByBusinessCode(String businessErrorCode) {
        return Arrays.stream(IPayErrorCode.values())
                .filter(e -> e.getBusinessErrorCode().equals(businessErrorCode))
                .findFirst()
                .orElse(null);
    }
}
