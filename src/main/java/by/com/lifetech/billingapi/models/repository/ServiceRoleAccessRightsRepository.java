package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.security.ServiceRoleAccessRightsEntity;
import by.com.lifetech.billingapi.models.entity.security.ServiceRoleAccessRightsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRoleAccessRightsRepository extends JpaRepository<ServiceRoleAccessRightsEntity, ServiceRoleAccessRightsKey> {
    @Query("""
              select s
              from ServiceRoleAccessRightsEntity s
              order by s.id.service
            """)
    List<ServiceRoleAccessRightsEntity> findAllByOrderByService();
}
