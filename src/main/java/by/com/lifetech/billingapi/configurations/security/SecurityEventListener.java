package by.com.lifetech.billingapi.configurations.security;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SecurityEventListener {
    private final Logger logger = LoggerFactory.getLogger(SecurityEventListener.class);

    @EventListener(AuthorizationDeniedEvent.class)
    public void onAuthorizationFailure(AuthorizationDeniedEvent event) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        logger.error("Unauthorized access attempt by user: {} with request: {} {}", username
                , request.getMethod(), request.getRequestURI());
    }
}