package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SALES_PORTAL_MIDS_V", schema = "BWSAPI")
@NoArgsConstructor
@AllArgsConstructor
public class SalesPortalMid {
    @Id
    @Column(name = "ID_MID")
    private String idMid;

    @Column(name = "NAME_MID_RU")
    private String nameMidRu;

    @Column(name = "NAME_TP_RU")
    private String nameTpRu;

    @Column(name = "PRICE_MID")
    private String priceMid;

    @Column(name = "PRICE_MID_CHARGING")
    private String priceMidCharging;
}
