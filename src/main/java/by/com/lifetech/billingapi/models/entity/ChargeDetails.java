package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChargeDetails {
	@Id
    @Column(name = "TARGET_BALANCE")
    private String balanceCode;
	
	@Transient
	private String balanceName;
	
	@Column(name = "BAL_BEFORE")
    private Double balanceBefore;
	
	@Column(name = "BAL_AFTER")
    private Double balanceAfter;
	
	@Column(name = "BALANCE_AMOUNT")
    private Double balanceAmount;
	
	@Column(name = "MEASURE_UNIT_CODE")
    private String measureUnitCode;
	
	@Transient
	private String measureUnitName;
	
	@Column(name = "RDM")
    private String servicePath;
}
