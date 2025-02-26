package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.dto.IPayFinancialTransactionDto;
import by.com.lifetech.billingapi.models.entity.IPayOnlineTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPayOnlineTransactionRepository extends JpaRepository<IPayOnlineTransaction, String> {
    @Query("""
            
            SELECT new by.com.lifetech.billingapi.models.dto.IPayFinancialTransactionDto(
            iot.code,
            fh.code,
            fh.linkedCode,
            iot.operationTypeCode,
            iot.operationResponseCode)
            FROM IPayOnlineTransaction iot
            LEFT JOIN FinancialHistoryTbl fh ON fh.code = iot.financialHistoryCode
            WHERE iot.transactionId = :operId
            """)
    List<IPayFinancialTransactionDto> getIPayFinTransactionByTransactionId(@Param("operId") String operId);

    Optional<IPayOnlineTransaction> findByCode(@Param("code") String code);
}
