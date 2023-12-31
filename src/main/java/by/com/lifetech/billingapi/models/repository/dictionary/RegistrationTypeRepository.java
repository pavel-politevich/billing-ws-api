package by.com.lifetech.billingapi.models.repository.dictionary;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.RegistrationType;


public interface RegistrationTypeRepository extends CrudRepository<RegistrationType, String> {

	@Cacheable(value = "registrationTypes", key = "#regTypeCode")
	RegistrationType findByRegistrationTypeCode(@Param("regTypeCode") String regTypeCode);
}
