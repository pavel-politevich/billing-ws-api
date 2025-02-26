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
public class ChargeSummary {
	@Id
    @Column(name = "EVENT_TYPE")
    private String eventGroupCode;
	
	@Transient
	private String eventGroupName;
	
	@Column(name = "SECONDS")
    private Double seconds;
	
	@Column(name = "BYTES")
    private Double bytes;
	
	@Column(name = "COUNT")
    private Double count;
	
	@Column(name = "LM")
    private Double lineMain;
	
	@Column(name = "LB")
    private Double lineBonus;
	
	@Column(name = "LE")
    private Double lineExtra;
	
	@Column(name = "CM")
    private Double commonMain;
	
	@Column(name = "CB")
    private Double commonBonus;
}
