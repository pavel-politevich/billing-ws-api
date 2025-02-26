package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "IPAY_ONLINE_TRANSACTIONS", schema = "TM_FM")
@NoArgsConstructor
@AllArgsConstructor
public class IPayOnlineTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE")
    private String code;

    @Column(name = "FINANCIAL_HISTORY_CODE")
    private String financialHistoryCode;

    @NotNull
    @Column(name = "ENTRY_DATE")
    private LocalDateTime entryDate;

    @Column(name = "AUDIT_DATE")
    private LocalDateTime auditDate;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "MSISDN")
    private String msisdn;

    @NotNull
    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @NotNull
    @Column(name = "OPERATION_TYPE_CODE")
    private String operationTypeCode;

    @Column(name = "OPERATION_RESPONSE_CODE")
    private String operationResponseCode;
}
