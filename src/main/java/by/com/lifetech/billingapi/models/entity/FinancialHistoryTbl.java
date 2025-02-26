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
@Table(name = "FINANCIAL_HISTORY", schema = "TM_FM")
@NoArgsConstructor
@AllArgsConstructor
public class FinancialHistoryTbl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE")
    private String code;

    @NotNull
    @Column(name = "ENTRY_DATE")
    private LocalDateTime entryDate;

    @Column(name = "CONTRACT_CODE")
    private String contractCode;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @NotNull
    @Column(name = "TYPE_CODE", length = 2)
    private String typeCode;

    @NotNull
    @Column(name = "REASON_CODE", length = 3)
    private String reasonCode;

    @NotNull
    @Column(name = "AUDIT_DATE")
    private LocalDateTime auditDate;

    @NotNull
    @Column(name = "SUBSCRIBER_LOCATION", length = 1)
    private String subscriberLocation;

    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "ADVANCE_AMOUNT")
    private BigDecimal advanceAmount;

    @Column(name = "DOCUMENT_NO")
    private String documentNo;

    @Column(name = "REFERENCE")
    private String reference;

    @NotNull
    @Column(name = "AGENT_NAME")
    private String agentName;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "NGS_CODE")
    private String ngsCode;

    @Column(name = "LINKED_CODE")
    private String linkedCode;

    @Column(name = "GOLD_REF_ID")
    private String goldRefId;

    @Column(name = "INVOICE_NO")
    private String invoiceNo;

    @Column(name = "PAYER_NAME")
    private String payerName;

    @Column(name = "BANK_BRANCH")
    private String bankBranchCode;
}