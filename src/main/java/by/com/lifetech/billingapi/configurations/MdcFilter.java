package by.com.lifetech.billingapi.configurations;

import java.io.IOException;

import by.com.lifetech.billingapi.configurations.properties.LogInfoConfig;
import org.slf4j.MDC;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter
public class MdcFilter extends HttpFilter {
    private static final long serialVersionUID = 1196284995672260349L;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            MDC.put(LogInfoConfig.TRANSACTION_ID_NAME, LogInfoConfig.getCorrelationId());
            MDC.put(LogInfoConfig.CURRENT_USER_NAME, LogInfoConfig.getCurrentUserName());
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(LogInfoConfig.TRANSACTION_ID_NAME);
            MDC.remove(LogInfoConfig.CURRENT_USER_NAME);
        }
    }
}
