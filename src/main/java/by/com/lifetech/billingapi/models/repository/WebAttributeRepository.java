package by.com.lifetech.billingapi.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.OmWebAttribute;

public interface WebAttributeRepository extends CrudRepository<OmWebAttribute, Long> {

	@Query(value = """
			select
				attr.id,
				attr.code_attributes,
				attr.om_code,
				attr.period,
				attr.value,
				attr.tp
			from tm_om.WEB_ATTR_VAL attr
			join tm_om.WEB_CODE_ATTR c on attr.code_attributes = c.code_attributes
			where attr.om_code = :productCode
				and c.channel = :channel
				and (attr.tp is null or attr.tp = :tp)
				and attr.period = :period
			
			union all
			
			select distinct t1.id + 9999 as id,
			        'CALL_ALL_NET' as code_attributes,
			        t1.om_code,
			        t1.period,
			        t1.value,
			        t1.tp
			      from tm_om.WEB_ATTR_VAL t1, tm_om.WEB_ATTR_VAL t2
			      where t1.om_code = t2.om_code
					  and t1.code_attributes = 'CALL_ONNET' and t1.value = 'Безлимит'
					  and t2.code_attributes = 'CALL_ONOTHNET' and t2.value = 'Безлимит'
					  and t1.om_code = :productCode
					  and t1.period = :period
			""", nativeQuery = true)
	List<OmWebAttribute> getAttrsByChannel(@Param("productCode") String productCode,@Param("channel") String channel, @Param("tp") String tp, @Param("period") String period);
}
