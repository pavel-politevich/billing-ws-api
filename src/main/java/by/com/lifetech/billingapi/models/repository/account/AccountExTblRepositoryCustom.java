package by.com.lifetech.billingapi.models.repository.account;

import by.com.lifetech.billingapi.models.enums.AccountTablesTypes;
import jakarta.persistence.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AccountExTblRepositoryCustom {
    List<Map<String, Object>> findAccountInfoByConditions(Map<String, Object> conditions, Set<String> columns);

    String getParameterFullName(String name, AccountTablesTypes type);

    List<Tuple> findAccountInfoByConditionsTuple(Map<String, Object> conditions, Set<String> columns);
}
