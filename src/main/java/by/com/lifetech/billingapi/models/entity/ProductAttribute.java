package by.com.lifetech.billingapi.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@IdClass(ProductAttributeId.class)
@Table(name = "OM_PRODUCT_ATTRIBUTES", schema = "tm_om")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute implements Serializable {
	@Serial
	private static final long serialVersionUID = -7956737193026412091L;

	@Id
	@Column(name = "OM_REF_CODE_VALUESCODE")
	private String code;
	
	@Column(name = "VALUE")
	private String value;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OM_PRODUCT_OFFERINGSCODE")
	@ToString.Exclude
	@JsonIgnore
	private ProductOffering productOffering;
}
