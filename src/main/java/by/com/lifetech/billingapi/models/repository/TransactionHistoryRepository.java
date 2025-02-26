package by.com.lifetech.billingapi.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.TransactionHistory;

import java.util.Optional;

public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Long>, JpaSpecificationExecutor<TransactionHistory> {
	
	@Query("""
			SELECT c FROM TransactionHistory c 
			WHERE (c.entryDate > TO_DATE(:startDate,'yyyy-MM-dd') and c.entryDate < TO_DATE(:endDate,'yyyy-MM-dd')) 
			  and (c.aMobileNo = :aMobileNo OR (c.aContractCode = :aContractCode and c.aMobileNo is null))
			  and (:transactionTypeCode is null or c.transactionTypeCode = :transactionTypeCode) 
			  and (:agentName is null or c.agentName = :agentName) 
			  and (c.entryDate >= 
			  ( 
			   SELECT min(h.entryDate) FROM RegistrationHistory h 
			   WHERE h.contractCode = :aContractCode and h.mobileNo = :aMobileNo
			  ) 
			  or :aContractCode is null)
			"""
			)
	Page<TransactionHistory> findAllByFields(
			@Param("startDate") String startDate,
            @Param("endDate") String endDate, 
            @Param("aMobileNo") String aMobileNo,
            @Param("aContractCode") String aContractCode,
            @Param("agentName") String agentName,
            @Param("transactionTypeCode") Long transactionTypeCode
            , Pageable pageable);

	@Query(value = """
			select th.code
			from tm_cim.transaction_history th
			join tm_om.om_product_interactions pi on pi.th_code = th.transaction_type_code
			and pi.product_code = :om_code
			and pi.action_code = 'ORDER'
			where th.a_contract_code = :contract_code and
			th.a_mobile_no = :msisdn
			and rownum = 1
			""", nativeQuery = true)
	Optional<Long> findServiceAnyActivation(@Param("msisdn")  String msisdn, @Param("contract_code")  String contractId, @Param("om_code")  String omCode);
}
