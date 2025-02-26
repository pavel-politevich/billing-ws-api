package by.com.lifetech.billingapi.models.entity.dictionary;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictRefundMoneyReasonRepository extends JpaRepository<DictRefundMoneyReason, String> {
    @Cacheable(value = "dictRefundMoneyReasonAll")
    List<DictRefundMoneyReason> findAll();
}