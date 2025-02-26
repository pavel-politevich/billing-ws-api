package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_FINANCIAL_TYPE", schema = "tm_fm")
public class DictFinancialType extends AbstracDictionary implements CodeNameDictionary{
    @Column(name="ALLOW_TO_ADD")
    private String allowToAdd;

    @Column(name="SIGN")
    private Double sign;

    @Column(name="PRESENT_IN_PAYMENTS")
    private String presentInPayments;

    @Column(name="PRESENT_IN_INVOICES")
    private String presentInInvoices;
}