package by.com.lifetech.billingapi.models.entity;

import by.com.lifetech.billingapi.models.entity.dictionary.CodeNameDictionary;
import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PRODUCT_OFFERING_CRM_V", schema = "tm_om")
@Data
@EqualsAndHashCode(exclude = {"attributes","productInfo"})
@NoArgsConstructor
@AllArgsConstructor
public class ProductOffering implements CodeNameDictionary, Serializable {
	@Serial
	private static final long serialVersionUID = -2771914078722311550L;

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "PRODUCT_SRECIFICATION_TYPE")
	private String productType;
	
	@Column(name = "GROUP_CODE")
	private String groupCode;

	@BatchSize(size = 30) 
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "productOffering")
	private List<ProductAttribute> attributes;
	
	@OneToOne(mappedBy = "productOffering")
	@BatchSize(size = 30) 
	@JoinColumn(name = "CODE")
	private ProductOfferingInfo productInfo;
	
	public String getNameByLang(Lang lang) {
		if (productInfo == null) {
			return null;
		}
		switch (lang) {
		case RU:
			return productInfo.getNameRu();
		case EN:
			return productInfo.getNameEn();
		default:
			return productInfo.getNameRu();
		}
	}

}
