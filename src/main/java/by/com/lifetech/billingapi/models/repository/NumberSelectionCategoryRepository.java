package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.NumberSelectionCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NumberSelectionCategoryRepository extends JpaRepository<NumberSelectionCategory, String> {
    @Transactional
    @Query(value = "select sim_stock.csp_api.confirmreservmsisdn(:msisdn) from dual", nativeQuery = true)
    String confirmReserveMsisdn(@Param("msisdn") String msisdn);

    @Transactional
    @Query(value = "select sim_stock.csp_api.lockReservedMsisdn(:msisdn, 'Life CRM') from dual", nativeQuery = true)
    String lockReservedMsisdn(@Param("msisdn") String msisdn);

    @Transactional
    @Query(value = "select sim_stock.csp_api.cancelReservMsisdn(:msisdn) from dual", nativeQuery = true)
    String cancelReserveMsisdn(@Param("msisdn") String msisdn);

    @Transactional
    @Query(value = "select sim_stock.csp_api.lockReservMsisdn(:reservationType) from dual", nativeQuery = true)
    String lockReserveRandomMsisdn(@Param("reservationType") String reservationType);

    Page<NumberSelectionCategory> findByCategory(String category, Pageable pageable);
}