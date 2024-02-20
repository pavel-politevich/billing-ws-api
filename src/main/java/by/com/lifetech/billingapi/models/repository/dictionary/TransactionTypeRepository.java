package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.TransactionType;

public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {
	
	@Cacheable(value = "transactionTypes", key = "#code")
	Optional<TransactionType> findByCode(@Param("code") Long code);

	@Cacheable(value = "thTypeNames")
	List<TransactionType> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);
}
