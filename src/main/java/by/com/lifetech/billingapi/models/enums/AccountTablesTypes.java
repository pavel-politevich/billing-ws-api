package by.com.lifetech.billingapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountTablesTypes {
    ACCOUNT("act"),
    ACCOUNT_TOP("act.topLevelAccount"),
    ACCOUNT_EX("aet"),
    ACCOUNT_EX_TOP("aet_top");

    private final String alias;
}
