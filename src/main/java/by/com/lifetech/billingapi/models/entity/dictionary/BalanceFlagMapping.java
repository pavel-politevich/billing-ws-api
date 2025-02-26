package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BALANCE_FLAG_MAPPING", schema = "tm_om")
public class BalanceFlagMapping {

	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="BALANCE_NAME")
	private String balanceName;
	
	@Column(name="BALANCE_FLAG")
	private String flagName;
}
