package by.com.lifetech.billingapi.configurations.security;

import javax.sql.DataSource;

import by.com.lifetech.billingapi.models.entity.security.ServiceRoleAccessRightsEntity;
import by.com.lifetech.billingapi.models.repository.ServiceRoleAccessRightsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.*;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ServiceRoleAccessRightsRepository accessRightsRepository;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from BWSAPI.users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username,authority from BWSAPI.authorities where username = ?");
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector).servletPath("/spring-mvc");
    }

    @Bean
    SecurityFilterChain appEndpoints(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.httpBasic(Customizer.withDefaults()).csrf(c -> c.disable()).authorizeHttpRequests(auth -> {
            for (SecurityEndpointRoles endpointRole : getEndpointsRolesMap().values()) {
                auth.requestMatchers(antMatcher(endpointRole.getService().getServicePath()))
                        .hasAnyRole(endpointRole.getRoles().stream().toArray(String[]::new));
            }
            auth.requestMatchers(antMatcher("/api/v1/**")).authenticated()
                    .anyRequest()
                    .denyAll();
        });
        return http.build();
    }

    private Map<String, SecurityEndpointRoles> getEndpointsRolesMap() {
        List<ServiceRoleAccessRightsEntity> accessRightsList = accessRightsRepository.findAllByOrderByService();
        if (accessRightsList == null || accessRightsList.isEmpty()) {
            throw new RuntimeException("Not received access rights rows from DB");
        }

        Map<String, SecurityEndpointRoles> endpointRolesMap = new HashMap<>();
        try {
            for (ServiceRoleAccessRightsEntity serviceRoleAccessRightsEntity : accessRightsList) {
                String serviceCode = serviceRoleAccessRightsEntity.getId().getServiceCode();
                if (endpointRolesMap.containsKey(serviceCode)) {
                    endpointRolesMap.get(serviceCode).getRoles().add(getRole(serviceRoleAccessRightsEntity));
                } else {
                    Set<String> rolesSet = new HashSet<>();
                    rolesSet.add(getRole(serviceRoleAccessRightsEntity));
                    endpointRolesMap.put(serviceCode, new SecurityEndpointRoles(rolesSet, serviceRoleAccessRightsEntity.getService()));
                }
            }
        } catch (Exception e) {
            logger.error("Security roles not received! Error: " + e.getMessage());
            throw new RuntimeException("Security roles not received! Error: " + e.getMessage());
        }
        return endpointRolesMap;
    }

    private String getRole(ServiceRoleAccessRightsEntity serviceRoleAccessRightsEntity) {
        return serviceRoleAccessRightsEntity.getId().getRoleCode().replace("ROLE_", "");
    }
}
