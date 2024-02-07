package by.com.lifetech.billingapi.models.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.TransactionHistory;

public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Long> {
	
	@Query("SELECT c FROM TransactionHistory c WHERE (c.entryDate > TO_DATE(:startDate,'yyyy-MM-dd') and c.entryDate < TO_DATE(:endDate,'yyyy-MM-dd')) "
			+ "and (c.aMobileNo = :aMobileNo) "
			+ "and (:aContractCode is null or c.aContractCode = :aContractCode) "
			+ "and (:transactionTypeCode is null or c.transactionTypeCode = :transactionTypeCode) "
			+ "and (:agentName is null or c.agentName = :agentName) "
			+ "order by c.Id"
			)
	Page<TransactionHistory> findAllByFields(
			@Param("startDate") String startDate,
            @Param("endDate") String endDate, 
            @Param("aMobileNo") String aMobileNo,
            @Param("aContractCode") String aContractCode,
            @Param("agentName") String agentName,
            @Param("transactionTypeCode") Long transactionTypeCode
            , Pageable pageable);
	
	Page<TransactionHistory> findAllByEntryDateBetweenAndAContractCode(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate, @Param("contractCode") Long transactionTypeCode, Pageable pageable);

}
