package by.com.lifetech.billingapi.models.entity.security;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SERVICE_ROLE_ACCESS_RIGHTS", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRoleAccessRightsEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 4552970860279593384L;

    @EmbeddedId
    private ServiceRoleAccessRightsKey id;
}