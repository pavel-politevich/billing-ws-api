package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.SubscriberSegment;

public interface SubscriberSegmentRepository extends CrudRepository<SubscriberSegment, String> {
	
	@Cacheable(value = "segments", key = "#code")
	Optional<SubscriberSegment> findByCode(@Param("code") String code);
	
	@Cacheable(value = "segmentNames")
	List<AbstracDictionary> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "segmentsAll")
	List<SubscriberSegment> findAll();
}
