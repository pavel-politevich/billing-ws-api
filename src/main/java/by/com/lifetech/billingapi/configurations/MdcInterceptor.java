package by.com.lifetech.billingapi.configurations;

import java.util.UUID;

import by.com.lifetech.billingapi.configurations.properties.TransactionIdConfig;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MdcInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(TransactionIdConfig.TRANSACTION_ID_NAME, getCorrelationId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TransactionIdConfig.TRANSACTION_ID_NAME);
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
