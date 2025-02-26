package by.com.lifetech.billingapi.models.repository.account;

import by.com.lifetech.billingapi.models.entity.AccountExTbl;
import by.com.lifetech.billingapi.models.entity.AccountTbl;
import by.com.lifetech.billingapi.models.enums.AccountTablesTypes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.*;

public class AccountExTblRepositoryCustomImpl implements AccountExTblRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    private final String FIND_ACTIVE_LINE_QUERY = """
                from AccountExTbl aet
                join aet.account act
                join AccountExTbl aet_top on aet_top.account = act.topLevelAccount
                where act.lcState <> 'TRM'
            """;

    @Override
    public List<Map<String, Object>> findAccountInfoByConditions(Map<String, Object> conditions, Set<String> columns) {
        TypedQuery<Object[]> query = getQuery(FIND_ACTIVE_LINE_QUERY, conditions, columns);
        List<Object[]> resultList = query.getResultList();
        return resultListArrayToListMap(resultList, columns);
    }

    @Override
    public String getParameterFullName(String name, AccountTablesTypes type) {
        return type.getAlias() + "." + name;
    }

    public List<Tuple> findAccountInfoByConditionsTuple(Map<String, Object> conditions, Set<String> columns) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<AccountExTbl> aet = cq.from(AccountExTbl.class);
        Join<AccountExTbl, AccountTbl> act = aet.join("account", JoinType.INNER);
        Root<AccountExTbl> aetTop = cq.from(AccountExTbl.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.notEqual(act.get("lcState"), "TRM"));
        predicates.add(cb.equal(act.get("topLevelAccount"), aetTop.get("account")));

        fillEqualConditions(cb, conditions, predicates, aet, act, aetTop);
        cq.where(predicates.toArray(new Predicate[0]));

        cq.multiselect(getSelectionByColumns(columns, aet, act, aetTop));

        return entityManager.createQuery(cq).getResultList();
    }

    private List<Selection<?>> getSelectionByColumns(Set<String> columns, Root<AccountExTbl> aet
            , Join<AccountExTbl, AccountTbl> act, Root<AccountExTbl> aetTop) {
        List<Selection<?>> selections = new ArrayList<>();
        for (String column : columns) {
            selections.add(getPath(column, aet, act, aetTop).alias(column));
        }
        return selections;
    }

    private void fillEqualConditions(CriteriaBuilder cb, Map<String, Object> conditions, List<Predicate> predicates, Root<AccountExTbl> aet
            , Join<AccountExTbl, AccountTbl> act, Root<AccountExTbl> aetTop) {
        for (Map.Entry<String, Object> condition : conditions.entrySet()) {
            String key = condition.getKey();
            Object value = condition.getValue();
            predicates.add(cb.equal(getPath(key, aet, act, aetTop), value));
        }
    }

    private List<Map<String, Object>> resultListArrayToListMap(List<Object[]> resultList, Set<String> columns) {
        List<Map<String, Object>> mappedResult = new ArrayList<>();
        for (Object[] row : resultList) {
            Map<String, Object> rowMap = new HashMap<>();
            int i = 0;
            for (String column : columns) {
                rowMap.put(column, row[i]);
                i++;
            }
            mappedResult.add(rowMap);
        }
        return mappedResult;
    }

    private TypedQuery<Object[]> getQuery(String basicQueryString, Map<String, Object> conditions, Set<String> columns) {
        StringBuilder queryBuilder = getQueryStringBuilder(basicQueryString, conditions, columns);
        TypedQuery<Object[]> query = entityManager.createQuery(queryBuilder.toString(), Object[].class);
        conditions.forEach((k, v) -> query.setParameter(getKeyName(k), v));
        return query;
    }

    private StringBuilder getQueryStringBuilder(String basicQueryString, Map<String, Object> conditions, Set<String> columns) {
        StringBuilder queryBuilder = new StringBuilder("select ");
        setSelectColumns(queryBuilder, columns);
        queryBuilder.append("\n").append(basicQueryString);
        setConditionsEquals(queryBuilder, conditions);
        return queryBuilder;
    }

    private void setConditionsEquals(StringBuilder queryBuilder, Map<String, Object> conditions) {
        conditions.forEach((k, v) -> queryBuilder.append("\nAND ")
                .append(k).append(" = :").append(getKeyName(k)));
    }

    private void setSelectColumns(StringBuilder queryBuilder, Set<String> columns) {
        boolean isFirstColumn = true;
        for (String column : columns) {
            if (!isFirstColumn) {
                queryBuilder.append(", ");
            } else {
                isFirstColumn = false;
            }
            queryBuilder.append(column);
        }
    }

    private String getKeyName(String key) {
        int lastIndex = key.lastIndexOf('.');
        return lastIndex >= 0 ? key.substring(lastIndex + 1) : key;
    }

    private Path<?> getPath(String columnFullName, Root<AccountExTbl> aet, Join<AccountExTbl, AccountTbl> act, Root<AccountExTbl> aetTop) {
        return switch (getTypeByColumnFullName(columnFullName)) {
            case ACCOUNT_EX_TOP -> aetTop.get(getKeyName(columnFullName));
            case ACCOUNT_EX -> aet.get(getKeyName(columnFullName));
            case ACCOUNT -> act.get(getKeyName(columnFullName));
            case ACCOUNT_TOP -> act.get("topLevelAccount").get(getKeyName(columnFullName));
        };
    }

    private AccountTablesTypes getTypeByColumnFullName(String columnFullName) {
        return Arrays.stream(AccountTablesTypes.values())
                .filter(type -> type.getAlias().equals(getPatByColumnFullName(columnFullName)))
                .findFirst()
                .orElseThrow();
    }

    private String getPatByColumnFullName(String columnFullName) {
        int lastIndex = columnFullName.lastIndexOf('.');
        return lastIndex >= 0 ? columnFullName.substring(0, lastIndex) : columnFullName;
    }
}
