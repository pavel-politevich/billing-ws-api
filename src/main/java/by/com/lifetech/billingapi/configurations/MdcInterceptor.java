package by.com.lifetech.billingapi.configurations;

import by.com.lifetech.billingapi.configurations.properties.LogInfoConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MdcInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(MdcInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.put(LogInfoConfig.TRANSACTION_ID_NAME, LogInfoConfig.getCorrelationId());
        MDC.put(LogInfoConfig.CURRENT_USER_NAME, LogInfoConfig.getCurrentUserName());
        long time = System.currentTimeMillis();
        MDC.put(LogInfoConfig.REQ_START_TIME, Long.toString(time));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        
        if (MDC.get(LogInfoConfig.TRANSACTION_ID_NAME) != null && MDC.get(LogInfoConfig.REQ_START_TIME) != null) {
            long time = System.currentTimeMillis() - Long.parseLong(MDC.get(LogInfoConfig.REQ_START_TIME));
            logger.info("Request time: {} ms.", time);
        }
        MDC.remove(LogInfoConfig.TRANSACTION_ID_NAME);
        MDC.remove(LogInfoConfig.CURRENT_USER_NAME);
        MDC.remove(LogInfoConfig.REQ_START_TIME);
    }
}
