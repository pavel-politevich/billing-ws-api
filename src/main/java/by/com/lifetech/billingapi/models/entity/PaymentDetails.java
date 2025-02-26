package by.com.lifetech.billingapi.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class PaymentDetails  {
    @Id
    @Column(name = "CODE")
    private String code;

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

    @Column(name = "FILE_STRING")
    private String fileString;

    @Column(name = "DOCUMENT_NO")
    private String documentNo;

    @JsonIgnore
    @Column(name = "BANK_BRANCH")
    private String bankBranch;

    @Column(name = "PAYER_NAME")
    private String payerName;

    @Column(name = "COMMENTS")
    private String comments;

    @Transient
    private List<LinkedPayment> linkedPayments;
}