package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.security.ServicesEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecurityServicesRepository extends JpaRepository<ServicesEntity, String>{
    @Cacheable(value = "noClearSecurityServices", cacheManager = "notExpireCacheManager")
    @Query("SELECT s FROM ServicesEntity s")
    List<ServicesEntity> findAllCacheble();
}
