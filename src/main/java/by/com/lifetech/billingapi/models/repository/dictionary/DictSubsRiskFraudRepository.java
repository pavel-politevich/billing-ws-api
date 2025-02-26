package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.DictSubsRiskFraud;
import by.com.lifetech.billingapi.models.entity.dictionary.LevelRiskFraud;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictSubsRiskFraudRepository extends JpaRepository<DictSubsRiskFraud, String> {
    @Cacheable(value = "subsRisksFraud", key = "#code")
    Optional<DictSubsRiskFraud> findByCode(@Param("code") String code);

    @Cacheable(value = "subsRiskFraudNames")
    List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);
}