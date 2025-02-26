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
@Table(name = "ss_sim_card", schema = "sim_stock")
public class SimCard {
    @Id
    @Column(name = "IMSI")
    private String imsi;
    @Column(name = "ICCID")
    private String iccid;
    @Column(name = "GSM_PIN1")
    private String pin1;
    @Column(name = "GSM_PIN2")
    private String pin2;
    @Column(name = "GSM_PUK1")
    private String puk1;
    @Column(name = "GSM_PUK2")
    private String puk2;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "SP_CODE")
    private String spCode;
    @Column(name = "BATCH_CODE")
    private String batchCode;
    @Column(name = "ORDER_ITEM_CODE")
    private String orderItemCode;
    @Column(name = "KI1")
    private String ki1;
}