package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CRM_SPECIAL_OFFERS_V", schema = "tm_om")
public class CrmSpecialOffers {
    @Id
    @Column(name = "MID")
    private String mid;

    @Column(name = "OFFER_TYPE")
    private String offerType;

    @Column(name = "OM_CODE")
    private String omCode;

    @Column(name = "TARIFF")
    private String tariffCode;

    @Column(name = "NEED_CHECK")
    private Boolean needCheck;
}