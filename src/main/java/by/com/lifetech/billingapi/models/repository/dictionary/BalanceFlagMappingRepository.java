package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.BalanceFlagMapping;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BalanceFlagMappingRepository extends CrudRepository<BalanceFlagMapping, Long> {
	
	@Cacheable(value = "balanceFlagMapps")
	List<BalanceFlagMapping> findByBalanceName(@Param("balanceName") String balanceName);
}
