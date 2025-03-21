package by.com.lifetech.billingapi.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "linked_payment")
public class LinkedPayment implements FinHistAttrs {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "ENTRY_DATE")
    private LocalDateTime entryDate;

    @Column(name = "AUDIT_DATE")
    private LocalDateTime auditDate;

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
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CONTRACT_CODE")
    private String contractCode;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "DOCUMENT_NO")
    private String documentNo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "AGENT_NAME")
    private String agentName;

    @Column(name = "REFERENCE")
    private String reference;

    @JsonIgnore
    @Column(name = "PAYMENT_AGENT_CODE")
    private String paymentAgentCode;

    @JsonIgnore
    @Column(name = "PAYMENT_SUBAGENT_CODE")
    private String paymentSubagentCode;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "PAYMENT_TOOL_TYPE_CODE")
    private String paymentToolTypeCode;

    @Column(name = "LEGAL_PERSON_UNN")
    private String legalPersonUnn;

    @Column(name = "PAYMENT_DESC_CODE")
    private String paymentDescCode;

    @Column(name = "PAYMENT_STRING")
    private String fileString;

    @Column(name = "COMMENTS")
    private String comments;

    @Transient
    private String bankBranchName;

    @Transient
    private String bankName;

    @Override
    public String getBankBranchCode() {
        return paymentSubagentCode;
    }

    @Override
    public String getBankCode() {
        return paymentAgentCode;
    }
}
