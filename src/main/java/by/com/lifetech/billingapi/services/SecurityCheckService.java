package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.models.entity.security.ServiceRoleAccessRightsEntity;
import by.com.lifetech.billingapi.models.entity.security.ServicesEntity;
import by.com.lifetech.billingapi.models.repository.SecurityServicesRepository;
import by.com.lifetech.billingapi.models.repository.ServiceRoleAccessRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service("SecurityCheckService")
@RequiredArgsConstructor
public class SecurityCheckService {
    Logger logger = LoggerFactory.getLogger(SecurityCheckService.class);
    private final SecurityServicesRepository securityServicesRepository;
    private final ServiceRoleAccessRepository serviceRoleAccessRepository;

    public boolean isValidAuthorities() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ServicesEntity service = findMatchingService(request.getRequestURI());
        if (service == null) {
            return false;
        }

        return checkRolesByService(service, authentication);
    }

    private ServicesEntity findMatchingService(String request) {
        List<ServicesEntity> services = securityServicesRepository.findAllCacheble();
        for (ServicesEntity service : services) {
            if (Pattern.matches(convertAntPatternToRegex(service.getServicePath()), request)) {
                return service;
            }
        }
        return null;
    }

    private boolean checkRolesByService(ServicesEntity service, Authentication authentication) {
        return !Collections.disjoint(getRolesSetByService(service), getRolesSetByAuthorities(authentication));
    }

    private Set<String> getRolesSetByService(ServicesEntity service) {
        List<ServiceRoleAccessRightsEntity> roleAccessRights = serviceRoleAccessRepository.findByService(service);
        return roleAccessRights.stream()
                .map(roleAccess -> roleAccess.getId().getRole().getCode())
                .collect(Collectors.toSet());
    }

    private Set<String> getRolesSetByAuthorities(Authentication authentication) {
        if (authentication.getAuthorities() == null) {
            return new HashSet<>();
        }
        return authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toSet());
    }

    private String convertAntPatternToRegex(String antPattern) {
        return antPattern.replace(".", "\\.")
                .replace("?", "\\?")
                .replace("/**","*")
                .replace("*", ".*");
    }
}
