package by.com.lifetech.billingapi.models.repository;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.Charges;
import by.com.lifetech.billingapi.models.entity.ChargesId;

public interface ChargesRepository extends CrudRepository<Charges, ChargesId> {

	@Query(value = """
			SELECT t.*, t.EVENT_START as EVENTSTART, ROWNUM AS rn, TM_CIM.GET_PNL_CODE(t.RDM) as PNL_CODE FROM
			(
			  SELECT * FROM TABLE(
			  TM_CIM.IUI3.GET_CHARGES_VF(:startDate, :endDate, :msisdn, :accId, tm_cim.GET_TOPACCID_BY_ACCID(:accId), :searchType, :includeAllLines, 'G', '', :onlyPaidEvents, :eventGroupCode)
			))t
			""", nativeQuery = true)
	Page<Charges> getCharges(
			@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("msisdn") String msisdn,
            @Param("accId") Long accId,
            @Param("searchType") String searchType,
            @Param("includeAllLines") String includeAllLines,
            @Param("onlyPaidEvents") String onlyPaidEvents,
            @Param("eventGroupCode") String eventGroupCode,
            Pageable pageable);
}
