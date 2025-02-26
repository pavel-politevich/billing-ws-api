package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "IUI_FT_REASONS", schema = "tm_fm")
public class DictFtReason extends AbstracDictionary implements CodeNameDictionary{
    @Column(name="ALLOW_TO_ADD")
    private String allowToAdd;

    @Column(name="USED_FOR_PAYMENTS")
    private String usedForPayments;

    @Column(name="USED_FOR_INVOICES")
    private String usedForInvoices;
}