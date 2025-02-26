package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.security.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRoleAccessRepository extends JpaRepository<ServiceRoleAccessRightsEntity, ServiceRoleAccessRightsKey> {
    @Cacheable(value = "noClearSecurityRoleAccess", key = "#service.code", cacheManager = "notExpireCacheManager")
    @Query("""
              select s
              from ServiceRoleAccessRightsEntity s
              where s.id.service = :service
            """)
    List<ServiceRoleAccessRightsEntity> findByService(@Param("service") ServicesEntity service);
}
