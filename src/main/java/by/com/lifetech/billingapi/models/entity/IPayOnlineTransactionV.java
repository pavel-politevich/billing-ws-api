package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "IPAY_ONLINE_TRANSACTIONS_V", schema = "TM_FM")
@NoArgsConstructor
@AllArgsConstructor
public class IPayOnlineTransactionV {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "AUDIT_DATE")
    private LocalDateTime auditDate;

    @Column(name = "DEPOSIT_STATUS")
    private String depositStatus;
}
