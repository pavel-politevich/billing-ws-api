package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.IPayOnlineTransactionV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPayOnlineTransactionVRepository extends JpaRepository<IPayOnlineTransactionV, String> {
    @Query("""
            SELECT i.depositStatus
            FROM IPayOnlineTransactionV i
            WHERE i.transactionId = :operId
            """)
    List<String> getDepositStatusByTransactionId(@Param("operId") String operId);
}
