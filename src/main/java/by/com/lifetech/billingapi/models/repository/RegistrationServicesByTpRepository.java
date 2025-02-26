package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.RegistrationServicesByTp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationServicesByTpRepository extends JpaRepository<RegistrationServicesByTp, String> {

    @Query(value = "select * from table(tm_om.get_iui_services_by_tp(:tariffPlan,:tid,:mid,:salesPoint))", nativeQuery = true)
    List<RegistrationServicesByTp> findIUIServices(@Param("tariffPlan") String tariffPlan, @Param("tid") String tid,
                                                    @Param("mid") String mid, @Param("salesPoint") String salesPoint);
}