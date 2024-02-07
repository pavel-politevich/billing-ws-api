package by.com.lifetech.billingapi.configurations.properties;

import org.slf4j.MDC;

public class TransactionIdConfig {
    public static final String TRANSACTION_ID_NAME = "CorrelationId";

    public static String getTransactionId () {
        return MDC.get(TRANSACTION_ID_NAME);
    }
}
