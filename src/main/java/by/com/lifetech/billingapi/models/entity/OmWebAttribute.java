package by.com.lifetech.billingapi.models.entity;

import by.com.lifetech.billingapi.models.entity.dictionary.DictWebAttribute;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "WEB_ATTR_VAL", schema = "tm_om")
public class OmWebAttribute {

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="OM_CODE")
	private String index;
	
	@Column(name="PERIOD")
	private String period;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="TP")
	private String tariff;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODE_ATTRIBUTES")
	@ToString.Exclude
	private DictWebAttribute attrCode;
	
}
