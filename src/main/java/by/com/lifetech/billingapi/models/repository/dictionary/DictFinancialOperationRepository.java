package by.com.lifetech.billingapi.models.repository.dictionary;

import by.com.lifetech.billingapi.models.entity.dictionary.AbstracDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.DictFinancialOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictFinancialOperationRepository extends JpaRepository<DictFinancialOperation, String>, JpaSpecificationExecutor<DictFinancialOperation> {
    @Cacheable(value = "dictFinOpMP")
    @Query("select c from DictFinancialOperation c where c.typeCode = 'MP' and c.allowToAdd ='Y'")
    List<AbstracDictionary> getForManualPayment();

    @Cacheable(value = "dictFinOpMC")
    @Query("select c from DictFinancialOperation c where c.isLegalEntity = :isLegalEntity and c.allowToAdd ='Y'")
    List<AbstracDictionary> getForManualCorrection(@Param("isLegalEntity") String isLegalEntity);

    @Cacheable(value = "dictFinOperations", key = "#code", condition = "#code != null")
    Optional<DictFinancialOperation> findByCode(@Param("code") String code);
}