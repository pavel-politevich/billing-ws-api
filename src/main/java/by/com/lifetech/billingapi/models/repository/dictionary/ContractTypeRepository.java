package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.ContractType;

public interface ContractTypeRepository extends CrudRepository<ContractType, String> {

	@Cacheable(value = "contractTypes", key = "#code")
	Optional<ContractType> findByCode(@Param("code") String code);
	
	@Cacheable(value = "contrTypeNames")
	List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);
}
