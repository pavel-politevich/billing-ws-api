package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.dto.OmPoInstanceValuesInfo;
import by.com.lifetech.billingapi.models.entity.OmPoInstanceValues;
import by.com.lifetech.billingapi.models.entity.PoInstanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OmPoInstanceValuesRepository extends JpaRepository<OmPoInstanceValues, PoInstanceId> {
    @Query("select o from OmPoInstanceValues o where o.deviceCode = ?1 and o.productCode = ?2 and o.tariffCode = ?3")
    List<OmPoInstanceValuesInfo> findByDeviceCodeAndProductCodeAndTariffCode(String deviceCode, String productCode, String tariffCode);
}