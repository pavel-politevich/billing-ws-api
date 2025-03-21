package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import by.com.lifetech.billingapi.models.entity.dictionary.HssAttribute;
import by.com.lifetech.billingapi.models.entity.dictionary.HssAttributeId;

public interface HssAttrRepository extends CrudRepository<HssAttribute, HssAttributeId> {
	
	@Cacheable(value = "hssAttrs")
	@Query(value = """
			select * from CONF.DICT_HLR_ATTRIBUTE t
			where t.code = :code 
			and (t.search_group_code = :groupCode or t.search_group_code = 'NONE')
			and t.group_code != 'Unknown'
			""", nativeQuery = true)
	Optional<HssAttribute> findByCodeAndSearchGroupCode(String code, String groupCode);
	
	@Cacheable(value = "hssCheckAttr")
	boolean existsByCode(String id);

	@Cacheable(value = "hssNames")
	List<HssAttribute> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "hssCheckAttrAll")
	List<HssAttribute> findAll();
}

