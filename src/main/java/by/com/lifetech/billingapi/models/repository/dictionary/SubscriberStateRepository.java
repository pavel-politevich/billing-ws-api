package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.SubscriberState;
import by.com.lifetech.billingapi.models.entity.dictionary.SubscriberStateId;

public interface SubscriberStateRepository extends CrudRepository<SubscriberState, SubscriberStateId> {
	@Cacheable(value = "subsStates")
	@Query(value = "select * from "
			+ "( "
			+ "select t.code, t.name_en, t.name_ru, "
			+ "nvl(t.reason_code,'STD') as reason_code "
			+ " from conf.DICT_SUBSCRIBER_STATE t "
			+ "where t.code = :code "
			+ "and (t.reason_code = :reason or t.reason_code is null) "
			+ "order by t.reason_code "
			+ ") "
			+ "where ROWNUM <= 1", nativeQuery = true
			)
	Optional<SubscriberState> findByCodeAndReason(@Param("code") String code, @Param("reason") String reason);
	
	@Cacheable(value = "subsStateNames")
	@Query(value = """
			select t.code, t.name_en, t.name_ru,
			nvl(t.reason_code,'STD') as reason_code
			from CONF.DICT_SUBSCRIBER_STATE t
			where upper(t.name_ru) like upper('%'|| :nameRu ||'%')
			or upper(t.name_en) like upper('%' || :nameEn ||'%')
			"""
		, nativeQuery = true)
	List<SubscriberState> findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(String nameRu, String nameEn);

	@Cacheable(value = "subscriberStateAll")
	@Query(value = """
			select t.code, t.name_en, t.name_ru,
			nvl(t.reason_code,'STD') as reason_code
			from CONF.DICT_SUBSCRIBER_STATE t
			"""
			, nativeQuery = true)
	List<SubscriberState> findAll();

}
