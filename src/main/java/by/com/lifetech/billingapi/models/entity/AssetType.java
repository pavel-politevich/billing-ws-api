package by.com.lifetech.billingapi.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ASSET_TYPES", schema = "TM_OM")
public class AssetType {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "ASSET_CLASS")
    private String assetClass;

    @Column(name = "SCREEN_NAME")
    private String name;

    @Column(name = "OFFERING_CODE")
    @JsonIgnore
    private String offeringCode;

    @Column(name = "SCORING_OFFER_GROUP")
    private String scoringGroup;

    @Column(name = "ID_GENERIC_NAME")
    private String genericId;
}