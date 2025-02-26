package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.SubscriptionType;

public interface SubscriptionTypeRepository extends CrudRepository<SubscriptionType, String> {
	
	@Cacheable(value = "subscriptionTypes", key = "#code")
	Optional<SubscriptionType> findByCode(@Param("code") String code);
	
	@Cacheable(value = "subscrTypeNames")
	List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "subscriptionTypesAll")
	List<SubscriptionType> findAll();

}
