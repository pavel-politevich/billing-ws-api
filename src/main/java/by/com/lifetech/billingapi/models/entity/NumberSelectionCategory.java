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
@Table(name = "NUMBER_SELECTION_CATEGORY_V", schema = "TM_CIM")
public class NumberSelectionCategory implements NumberSelection {
    @Id
    @Column(name="MSISDN")
    private String msisdn;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="PRICE")
    private String price;
}