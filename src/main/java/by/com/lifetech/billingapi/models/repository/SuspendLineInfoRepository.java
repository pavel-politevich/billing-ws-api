package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.SuspendLineInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SuspendLineInfoRepository extends JpaRepository<SuspendLineInfoEntity, Long> {
    List<SuspendLineInfoEntity> findByMsisdn(@Param("msisdn") String msisdn);
}
