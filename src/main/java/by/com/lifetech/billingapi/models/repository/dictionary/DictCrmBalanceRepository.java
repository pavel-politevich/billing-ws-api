package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.DictCrmBalance;

public interface DictCrmBalanceRepository extends CrudRepository<DictCrmBalance, String> {
	
	@Cacheable(value = "dictBalances", key = "#code")
	Optional<DictCrmBalance> findByCode(@Param("code") String code);
	
	@Cacheable(value = "plNames", key = "#code")
	@Query(value = "select t.pl_name from tm_om.dict_pl_name t where t.pocket_label = :code", nativeQuery = true)
	Optional<String> getPocketName(@Param("code") String code);

	@Cacheable(value = "mainScreenBalances")
	List<DictCrmBalance> findByPresentInMainScreenNotNull();
}
