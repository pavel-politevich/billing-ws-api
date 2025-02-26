package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.DictFinancialType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DictFinancialTypeRepository extends JpaRepository<DictFinancialType, String> {
    @Cacheable(value = "dictFinType", key = "#code", condition = "#code != null")
    Optional<DictFinancialType> findByCode(@Param("code") String code);
}