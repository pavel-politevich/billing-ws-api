package by.com.lifetech.billingapi.configurations;

import java.io.IOException;
import java.util.UUID;

import by.com.lifetech.billingapi.configurations.properties.TransactionIdConfig;
import org.slf4j.MDC;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter
public class MdcFilter extends HttpFilter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1196284995672260349L;

	@Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
        try {
            MDC.put(TransactionIdConfig.TRANSACTION_ID_NAME, getCorrelationId());
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(TransactionIdConfig.TRANSACTION_ID_NAME);
        }
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
