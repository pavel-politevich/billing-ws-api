package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_FINANCIAL_OPERATION", schema = "tm_fm")
public class DictFinancialOperation extends AbstracDictionary implements CodeNameDictionary {
    @Column(name="SIGN")
    private Integer sign;

    @Column(name="OPSC_GOLD_OPERATION")
    private String opscGoldOperation;

    @Column(name="IS_REFER")
    private String isRefer;

    @Column(name="IS_WRITEOF_APPL")
    private String isWriteofAppl;

    @Column(name="PRESENT_IN_PAYMENTS")
    private String presentInPayments;

    @Column(name="PRESENT_IN_INVOICES")
    private String presentInInvoices;

    @Column(name="TYPE_CODE")
    private String typeCode;

    @Column(name="REASON_CODE")
    private String reasonCode;

    @Column(name="ALLOW_TO_ADD")
    private String allowToAdd;

    @Column(name="FN_GROUP_TO_ADD")
    private String fnGroupToAdd;

    @Column(name="ALLOW_MT_CON")
    private String allowMtCon;

    @Column(name="ALLOW_MT_MSISDN")
    private String allowMtMsisdn;

    @Column(name="FO_GROUP_CODE")
    private String foGroupCode;

    @Column(name="IS_LEGAL_ENTITY")
    private String isLegalEntity;
}