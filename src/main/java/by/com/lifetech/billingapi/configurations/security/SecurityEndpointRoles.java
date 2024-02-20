package by.com.lifetech.billingapi.configurations.security;

import by.com.lifetech.billingapi.models.entity.security.ServicesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SecurityEndpointRoles {
    private Set<String> roles;
    private ServicesEntity service;
}
