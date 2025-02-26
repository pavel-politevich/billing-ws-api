package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import by.com.lifetech.billingapi.models.entity.dictionary.PcrfAttribute;

public interface PcrfAttrRepository extends CrudRepository<PcrfAttribute, String> {
	
	@Cacheable(value = "pcrfAttrs")
	Optional<PcrfAttribute> findById(String Id);
	
	@Cacheable(value = "pcrfCheckAttr")
	boolean existsById(String id);
	
	@Cacheable(value = "pcrfNames")
	List<PcrfAttribute> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "pcrfNamesAll")
	List<PcrfAttribute> findAll();
}
