package by.com.lifetech.billingapi.models.repository.account;

import by.com.lifetech.billingapi.models.entity.AccountExTbl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountExTblRepository extends JpaRepository<AccountExTbl, Long>, AccountExTblRepositoryCustom {
}
