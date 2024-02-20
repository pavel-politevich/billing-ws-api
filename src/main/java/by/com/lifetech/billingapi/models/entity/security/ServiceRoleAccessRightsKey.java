package by.com.lifetech.billingapi.models.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRoleAccessRightsKey implements Serializable {
    @Column(name = "service_code", insertable = false, updatable = false)
    private String serviceCode;

    @Column(name = "role_code", insertable = false, updatable = false)
    private String roleCode;
}
