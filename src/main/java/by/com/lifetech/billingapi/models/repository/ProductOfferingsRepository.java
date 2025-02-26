package by.com.lifetech.billingapi.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.ProductOffering;

public interface ProductOfferingsRepository extends CrudRepository<ProductOffering, String> {
	
	@Cacheable(value = "productOfferings")
	Optional<ProductOffering> findById(String id);

	@Cacheable(value = "availableProducts")
	@Query(value = """
			select t.code from tm_om.OM_PRODUCT_OFFERINGS t
			join tm_om.om_channel_registry cr on t.code = cr.product_code
			where t.code in
			(
				select target_product_offering
				from tm_om.om_product_relationship pr
				join tm_om.om_relationship_attributes ra on ra.product_relationship_code = pr.code
				where pr.source_product_offering = :tariffCode
				and pr.type = 'ALLOW_TO_ORDER'
				and ra.om_ref_code_valuescode = 'CATEGORY_TYPE'
				and (ra.value = 'BOUTH' or ra.value = :contractTypeCode)
			)
			and cr.rc_type_code = :channelCode
			""", nativeQuery = true)
	List<String> getAvailableProducts(@Param("tariffCode") String tariffCode,
			@Param("contractTypeCode") String contractTypeCode, @Param("channelCode") String channelCode);

	@Cacheable(value = "productattrValues")
	@Query(value = """
			select t.value
			from tm_om.om_product_attributes t
			where t.om_product_offeringscode = :productCode
			and t.om_ref_code_valuescode = :attrCode
			""", nativeQuery = true)
	String getProductAttribute(@Param("productCode") String productCode, @Param("attrCode") String attrCode);

	@Query(value = """
			select pa.om_product_offeringscode
			from tm_om.om_product_attributes pa
			where pa.om_ref_code_valuescode = :attrCode
			and pa.value = :value
			""", nativeQuery = true)
	Optional<String> getProductCodeByAttributeValue(@Param("attrCode") String attrCode, @Param("value") String value);
}
