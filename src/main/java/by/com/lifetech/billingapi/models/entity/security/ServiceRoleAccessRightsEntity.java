package by.com.lifetech.billingapi.models.entity.security;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SERVICE_ROLE_ACCESS_RIGHTS", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRoleAccessRightsEntity {
    @EmbeddedId
    private ServiceRoleAccessRightsKey id;

    @ManyToOne
    @JoinColumn(name = "service_code", nullable = false)
    private ServicesEntity service;

    @ManyToOne
    @JoinColumn(name = "role_code", nullable = false)
    private RolesGroupEntity role;

    @Column(name = "change_desc")
    private String changeDesc;

    @Column(name = "change_date")
    private LocalDateTime changeDate;
}