package by.com.lifetech.billingapi.models.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.RegistrationHistory;


public interface RegistrationHistoryRepository extends CrudRepository<RegistrationHistory, Long>, JpaSpecificationExecutor<RegistrationHistory> {
	
	Page<RegistrationHistory> findAllByRegistrationDateBetween(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate, Pageable pageable);
	
	Page<RegistrationHistory> findAllByRegistrationDateBetweenAndSalesPoint(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate, @Param("salesPoint") String salesPoint, Pageable pageable);
	
	
	@Query("SELECT c FROM RegistrationHistory c WHERE (:salesPoint is null or c.salesPoint = :salesPoint) "
			+ "and (:tariffCode is null or c.tariffCode = :tariffCode) "
			+ "and (:agent is null or c.agent = :agent) "
			+ "and (:categoryCode is null or c.categoryCode = :categoryCode) "
			+ "and (:contractCode is null or c.contractCode = :contractCode) "
			+ "and (:registrationTypeCode is null or c.registrationTypeCode = :registrationTypeCode) "
			+ "and (c.entryDate between TO_DATE(:startDate,'yyyy-MM-dd') and TO_DATE(:endDate,'yyyy-MM-dd')) "
			)
	Page<RegistrationHistory> findAllByFields(
			@Param("startDate") String startDate,
            @Param("endDate") String endDate, 
            @Param("salesPoint") String salesPoint,
            @Param("tariff") String tariffCode,
            @Param("agent") String agent,
            @Param("category") String categoryCode,
            @Param("registrationType") String registrationTypeCode,
            @Param("contractCode") String contractCode
            , Pageable pageable);

}
