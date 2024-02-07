package by.com.lifetech.billingapi.models.repository;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.com.lifetech.billingapi.models.entity.RegistrationHistory;


public interface RegistrationHistoryRepository extends CrudRepository<RegistrationHistory, Long> {
	
	Page<RegistrationHistory> findAllByRegistrationDateBetween(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate, Pageable pageable);
	
	Page<RegistrationHistory> findAllByRegistrationDateBetweenAndSalesPoint(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate, @Param("salesPoint") String salesPoint, Pageable pageable);
	
	
	@Query("SELECT c FROM RegistrationHistory c WHERE (:salesPoint is null or c.salesPoint = :salesPoint) "
			+ "and (:tariff is null or c.tariff = :tariff) "
			+ "and (:agent is null or c.agent = :agent) "
			+ "and (:category is null or c.category = :category) "
			+ "and (:contractCode is null or c.contractCode = :contractCode) "
			+ "and (:registrationType is null or c.registrationType = :registrationType) "
			+ "and (c.entryDate between TO_DATE(:startDate,'yyyy-MM-dd') and TO_DATE(:endDate,'yyyy-MM-dd')) "
			)
	Page<RegistrationHistory> findAllByFields(
			@Param("startDate") String startDate,
            @Param("endDate") String endDate, 
            @Param("salesPoint") String salesPoint,
            @Param("tariff") String tariff,
            @Param("agent") String agent,
            @Param("category") String category,
            @Param("registrationType") String registrationType,
            @Param("contractCode") String contractCode
            , Pageable pageable);

}
