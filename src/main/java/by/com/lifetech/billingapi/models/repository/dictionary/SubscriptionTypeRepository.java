package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.SubscriptionType;

public interface SubscriptionTypeRepository extends CrudRepository<SubscriptionType, String> {
	
	@Cacheable(value = "subscriptionTypes", key = "#code")
	Optional<SubscriptionType> findByCode(@Param("code") String code);

}
