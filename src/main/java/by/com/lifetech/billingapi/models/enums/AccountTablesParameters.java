package by.com.lifetech.billingapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountTablesParameters {
    MOBILE_NO(AccountTablesTypes.ACCOUNT_EX.getAlias().concat(".").concat("mobileNo")),
    PASSPORT_TYPE(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("passportType")),
    IDENTIFICATION_CODE(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("identificationCode")),
    PASSPORT_NUMBER(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("passportNo")),
    PASSPORT_SERIES(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("passportSeries")),
    FIRST_NAME(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("firstName")),
    MID_NAME(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("midName")),
    LAST_NAME(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("lastName")),
    CONTRACT_TYPE(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("contractType")),
    CONTRACT_ID(AccountTablesTypes.ACCOUNT_EX_TOP.getAlias().concat(".").concat("contractId"));

    private String fullName;
}
