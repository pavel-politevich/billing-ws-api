package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.DictMeasureUnit;

public interface DictMeasureUnitRepository extends CrudRepository<DictMeasureUnit, String> {
	
	@Cacheable(value = "measureUnits", key = "#code")
	Optional<DictMeasureUnit> findByCode(@Param("code") String code);
	
	@Cacheable(value = "measureUnitNames")
	List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);
}
