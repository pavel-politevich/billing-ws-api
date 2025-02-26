package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.LevelRiskFraud;

public interface LevelRiskFraudRepository extends CrudRepository<LevelRiskFraud, String> {
	
	@Cacheable(value = "levelRisks", key = "#code")
	Optional<LevelRiskFraud> findByCode(@Param("code") String code);

	@Cacheable(value = "levelRiskNames")
	List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "levelRiskAll")
	List<LevelRiskFraud> findAll();
}
