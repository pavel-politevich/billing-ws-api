package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.SubscriberState;

public interface SubscriberStateRepository extends CrudRepository<SubscriberState, String> {
	@Cacheable(value = "subsStates")
	@Query(value = "select * from "
			+ "( "
			+ "select * from conf.DICT_SUBSCRIBER_STATE t "
			+ "where t.code = :code "
			+ "and (t.reason_code = :reason or t.reason_code is null) "
			+ "order by t.reason_code "
			+ ") "
			+ "where ROWNUM <= 1", nativeQuery = true
			)
	Optional<SubscriberState> findByCodeAndReason(@Param("code") String code, @Param("reason") String reason);

}
