package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "OM_POI_INSTANCE_VALUES_V", schema = "TM_OM")
@IdClass(PoInstanceId.class)
public class OmPoInstanceValues {
    @Id
    @Column(name = "DEVICE_CODE")
    private String deviceCode;

    @Id
    @Column(name = "OM_PRODUCT_OFFERING_CODE")
    private String productCode;

    @Id
    @Column(name = "OM_TARIFF_CODE")
    private String tariffCode;

    @Id
    @Column(name = "CODE")
    private String attrCode;

    @Column(name = "VALUE")
    private String value;
}