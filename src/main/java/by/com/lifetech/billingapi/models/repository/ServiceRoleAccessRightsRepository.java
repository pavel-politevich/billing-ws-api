package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.security.ServiceRoleAccessRightsEntity;
import by.com.lifetech.billingapi.models.entity.security.ServiceRoleAccessRightsKey;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Cacheable(value = "securityRights")
public interface ServiceRoleAccessRightsRepository extends JpaRepository<ServiceRoleAccessRightsEntity, ServiceRoleAccessRightsKey> {
    List<ServiceRoleAccessRightsEntity> findAllByOrderByService();
}
