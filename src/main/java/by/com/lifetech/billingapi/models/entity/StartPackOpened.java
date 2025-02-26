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
@Table(name = "CRM_START_PACK_OPENED_V", schema = "TM_OM")
public class StartPackOpened {
    @Id
    @Column(name = "CODE")
    private String code;
    @Column(name = "METHOD_OF_SALE")
    private String methodOfSale;
    @Column(name = "TARIFF")
    private String tariffCode;
    @Column(name = "CUSTOMER_TYPE")
    private String customerType;
    @Column(name = "AGE")
    private int age;
    @Column(name = "IUI_COST")
    private String iuiCost;
    @Column(name = "IS_NONRESIDENT")
    private boolean nonresident;
    @Column(name = "ASSET_CODE")
    private String deviceCode;
    @Column(name = "SCREEN_NAME")
    private String deviceName;
    @Column(name = "SCORING_OFFER_GROUP")
    private String scoringGroup;
    @Column(name = "ID_GENERIC_NAME")
    private String genericId;
}