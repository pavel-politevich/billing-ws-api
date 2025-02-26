package by.com.lifetech.billingapi.configurations.properties;

import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class LogInfoConfig {
    public static final String TRANSACTION_ID_NAME = "CorrelationId";
    public static final String CURRENT_USER_NAME = "user";
    public static final String REQ_START_TIME = "startTime";

    private LogInfoConfig() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTransactionId () {
        return MDC.get(TRANSACTION_ID_NAME);
    }
    
    public static String getMDCUserName () {
        return MDC.get(CURRENT_USER_NAME);
    }

    public static String getCorrelationId() {
        return UUID.randomUUID().toString();
    }

    public static String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String getReqStartTime () {
        return MDC.get(REQ_START_TIME);
    }
}
