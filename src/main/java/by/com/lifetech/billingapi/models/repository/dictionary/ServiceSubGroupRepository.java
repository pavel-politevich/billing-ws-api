package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import by.com.lifetech.billingapi.models.entity.dictionary.DictServiceSubGroup;

public interface ServiceSubGroupRepository extends CrudRepository<DictServiceSubGroup, String> {

	@Cacheable(value = "iuiSubgroups")
	Optional<DictServiceSubGroup> findById(String Id);
	
	@Cacheable(value = "subgroupsByGroup")
	List<DictServiceSubGroup> findByGroupId(int id);

	@Cacheable(value = "subgroupNames")
	List<DictServiceSubGroup> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "iuiSubgroupsAll")
	List<DictServiceSubGroup> findAll();
}
