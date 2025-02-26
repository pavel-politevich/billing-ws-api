package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.DictChargingEventGroup;

public interface ChargingEventGroupRepository extends CrudRepository<DictChargingEventGroup, String> {
	
	@Cacheable(value = "chargeEventTypes", key = "#code")
	Optional<DictChargingEventGroup> findByCode(@Param("code") String code);
	
	@Cacheable(value = "chargeEventTypeNames")
	List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "chargeEventTypesAll")
    List<DictChargingEventGroup> findAll();

	List<DictChargingEventGroup> findByParentCode(String parentCode);
}
