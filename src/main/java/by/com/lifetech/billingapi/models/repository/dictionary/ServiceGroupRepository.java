package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import by.com.lifetech.billingapi.models.entity.dictionary.DictServiceGroup;

public interface ServiceGroupRepository extends CrudRepository<DictServiceGroup, Integer> {

	@Cacheable(value = "iuiGroups")
	Optional<DictServiceGroup> findById(int Id);
	
	@Cacheable(value = "groupNames")
	List<DictServiceGroup> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "iuiGroupsAll")
	List<DictServiceGroup> findAll();
}
