package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "CRM_FINANCIAL_HISTORY_V", schema = "tm_fm")
public class FinancialHistory implements FinHistAttrs {
    @Id
    @NotNull
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
    @Column(name = "TYPE_CODE")
    private String typeCode;

    @Transient
    private String typeName;

    @NotNull
    @Column(name = "REASON_CODE")
    private String reasonCode;

    @Transient
    private String reasonName;

    @NotNull
    @Column(name = "AUDIT_DATE")
    private LocalDateTime auditDate;

    @Column(name = "SUBSCRIBER_LOCATION")
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

    @Column(name = "AGENT_NAME")
    private String agentName;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "PAYER_NAME")
    private String payerName;

    @Column(name = "BANK_BRANCH")
    private String bankBranchCode;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LINKED_CODE")
    private String linkedCode;

    @Column(name = "INVOICE_NO")
    private String invoiceNo;

    @Column(name = "IS_WRITEOF_APPL")
    private String isWriteOfAppl;

    @Transient
    private String bankBranchName;

    @Transient
    private String bankName;

    @Override
    public String getBankCode() {
        return reference;
    }
}