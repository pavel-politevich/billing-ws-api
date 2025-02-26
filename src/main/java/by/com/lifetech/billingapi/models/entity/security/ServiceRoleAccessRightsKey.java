package by.com.lifetech.billingapi.models.entity.security;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRoleAccessRightsKey implements Serializable {
    @Serial
    private static final long serialVersionUID = -575606656206602228L;

    @ManyToOne
    @JoinColumn(name = "service_code", nullable = false)
    private ServicesEntity service;

    @ManyToOne
    @JoinColumn(name = "role_code", nullable = false)
    private RolesGroupEntity role;
}
