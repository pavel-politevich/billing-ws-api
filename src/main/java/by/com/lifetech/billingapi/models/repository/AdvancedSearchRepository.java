package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.AdvancedSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AdvancedSearchRepository extends JpaRepository<AdvancedSearch, Long>, JpaSpecificationExecutor<AdvancedSearch> {

    Optional<AdvancedSearchFIO> findDistinctByAccountId(Long accountId);
}