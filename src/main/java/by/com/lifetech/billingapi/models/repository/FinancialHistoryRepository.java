package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.FinancialHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialHistoryRepository extends JpaRepository<FinancialHistory, String>, JpaSpecificationExecutor<FinancialHistory> {
}
