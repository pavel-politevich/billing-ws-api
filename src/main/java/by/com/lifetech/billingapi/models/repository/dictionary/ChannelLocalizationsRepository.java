package by.com.lifetech.billingapi.models.repository.dictionary;

import java.util.Collection;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.dictionary.ChannelLocalizations;
import by.com.lifetech.billingapi.models.entity.dictionary.UniversalDictionary;

public interface ChannelLocalizationsRepository extends CrudRepository<ChannelLocalizations, String> {

	@Cacheable(value = "chLocal", key = "#productCode", condition = "#productCode != null")
	@Query(value = "select * from tm_om.om_channel_localizations tch where tch.registry_code = "
			+ "(select t.code from tm_om.om_channel_registry t where t.product_code = :product_code"
			+ " and t.rc_type_code = :channelCode)", nativeQuery = true)
	List<ChannelLocalizations> findChannelLocalizations(@Param("product_code") String productCode,
			@Param("channelCode") String channelCode);

	@Cacheable(value = "chLocNames")
	@Query(value = """
					select tr.product_code as code, t.value as name from tm_om.om_channel_localizations t
					join tm_om.om_channel_registry tr on t.registry_code = tr.code
					where upper(t.value) like upper('%' || :name || '%') and tr.rc_type_code = :channelCode
					and tr.product_code in
					(
					  select p.code from tm_om.om_product_offerings p
					  where p.product_srecification_type = :specType
					)
			""", nativeQuery = true)
	Collection<UniversalDictionary> findProductByName(@Param("name") String name,
			@Param("channelCode") String channelCode, @Param("specType") String specType);

}
