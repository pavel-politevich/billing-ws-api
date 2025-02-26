package by.com.lifetech.billingapi.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "OM_PRODUCT_RELATIONSHIP_V", schema = "tm_om")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TariffRelationship {

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "SOURCE_PRODUCT_OFFERING")
	private String sourceProduct;
	
	@Column(name = "TARGET_PRODUCT_OFFERING")
	private String targetProduct;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "COST")
	private String cost;
	
	@Column(name = "MID")
	private String mid;

	@Column(name = "NON_RESIDENT")
	private int nonResident;
}
