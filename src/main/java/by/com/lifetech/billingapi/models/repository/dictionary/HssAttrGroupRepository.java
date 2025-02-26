package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import by.com.lifetech.billingapi.models.entity.dictionary.HssAttributuGroup;

public interface HssAttrGroupRepository extends CrudRepository<HssAttributuGroup, String> {

	@Cacheable(value = "hssAttrGroups")
	Optional<HssAttributuGroup> findById(String Id);
	
	@Cacheable(value = "hssCheckAttrGroup")
	boolean existsById(String id);
}
