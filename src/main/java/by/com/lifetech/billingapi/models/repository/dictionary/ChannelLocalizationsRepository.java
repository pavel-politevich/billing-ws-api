package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.ChannelLocalizations;

public interface ChannelLocalizationsRepository extends CrudRepository<ChannelLocalizations, String> {

	@Cacheable(value = "localNames", key = "#productCode", condition="#productCode != null")
	@Query(value = "select * from tm_om.om_channel_localizations tch where tch.registry_code = "
	+ "(select t.code from tm_om.om_channel_registry t where t.product_code = :product_code"
	+ " and t.rc_type_code = :channelCode)", nativeQuery = true)
	List<ChannelLocalizations> findChannelLocalizations(@Param("product_code") String productCode,
	@Param("channelCode") String channelCode);
	

}
