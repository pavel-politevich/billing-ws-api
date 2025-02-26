package by.com.lifetech.billingapi.models.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.ChargeDetails;

public interface ChargeDetailsRepository extends CrudRepository<ChargeDetails, String> {
	
	@Query(value = """
			SELECT * FROM TABLE(
			  TM_CIM.IUI3.GET_CHARGE_DETAILS_VF(:startTime, :msisdn, :accId, tm_cim.GET_TOPACCID_BY_ACCID(:accId), :tid, :searchType, 'G')
			)
			""", nativeQuery = true)
	List<ChargeDetails> getChargeDetails(
			@Param("startTime") LocalDateTime startTime,
            @Param("msisdn") String msisdn,
            @Param("accId") Long accId,
            @Param("tid") String tid,
            @Param("searchType") String searchType);
}
