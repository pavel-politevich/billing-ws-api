package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.AssetType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetTypeRepository extends JpaRepository<AssetType, String> {

    @Cacheable(value = "assetTypeOtp")
    @Query(value = """
        select t.*
         from tm_om.asset_type_to_rule r
         join tm_om.asset_types t on r.types_code = t.code
         where r.rules_code = 'CONDITION_WITHOUT_OBLIGATION_FOR_NEW'
    """, nativeQuery = true)
    List<AssetType> findOpenedOtpDevices();

    @Cacheable(value = "assetTypes")
    AssetType findByCode(String code);
}