package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.Collection;
import java.util.List;

import by.com.lifetech.billingapi.models.dto.UniversalDictionary;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.ChannelLocalizations;
import by.com.lifetech.billingapi.models.entity.dictionary.ChannelLocalizationsId;


public interface ChannelLocalizationsRepository extends CrudRepository<ChannelLocalizations, ChannelLocalizationsId> {

	@Cacheable(value = "chLocal", condition = "#productCode != null")
	@Query(value = "select * from tm_om.om_channel_localizations tch where tch.registry_code = "
			+ "(select t.code from tm_om.om_channel_registry t where t.product_code = :product_code"
			+ " and t.rc_type_code = :channelCode)", nativeQuery = true)
	List<ChannelLocalizations> findChannelLocalizations(@Param("product_code") String productCode,
			@Param("channelCode") String channelCode);

	@Cacheable(value = "chLocNames")
	@Query(name = "ChannelLocalizations.findProductByName", nativeQuery = true)
	Collection<UniversalDictionary> findProductByName(@Param("name") String name,
													  @Param("channelCode") String channelCode, @Param("specType") String specType);

	@Cacheable(value = "chLocByCode")
	@Query(name = "ChannelLocalizations.findProductByCode", nativeQuery = true)
	Collection<UniversalDictionary> findProductByCode(@Param("code") String code,
													  @Param("channelCode") String channelCode, @Param("specType") String specType,
													  @Param("lang") String lang);

}
