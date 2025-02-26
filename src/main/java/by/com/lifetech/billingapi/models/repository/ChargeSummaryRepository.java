package by.com.lifetech.billingapi.models.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.ChargeSummary;

public interface ChargeSummaryRepository extends CrudRepository<ChargeSummary, String> {

	@Query(value = """
			SELECT * FROM TABLE(
			TM_CIM.IUI3.GET_CHARGES_SUMMARY_VF(
			  :startDate, :endDate, :msisdn, :accId, tm_cim.GET_TOPACCID_BY_ACCID(:accId), :searchType, :includeAllLines, 'G', '', :onlyPaidEvents, :eventGroupCode)
			)
			""", nativeQuery = true)
	List<ChargeSummary> getChargeSummary(
			@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("msisdn") String msisdn,
            @Param("accId") Long accId,
            @Param("searchType") String searchType,
            @Param("includeAllLines") String includeAllLines,
            @Param("onlyPaidEvents") String onlyPaidEvents,
            @Param("eventGroupCode") String eventGroupCode);
}
