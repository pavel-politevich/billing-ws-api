package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "DICT_CRM_BALANCE_V", schema = "conf")
public class DictCrmBalance implements CodeNameDictionary {

	@Id
	@Column(name="BALANCE_CODE")
	private String code;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Column(name="BALANCE_GROUP_CODE")
	private String groupCode;
	
	@Column(name="GROUP_NAME")
	private String groupName;
	
	@Column(name="BALANCE_ORDER")
	private int sort;
	
	@Column(name="GROUP_ORDER")
	private int groupOrder;
	
	@Column(name="MEASURE_CODE")
	private String measureCode;
	
	@Column(name="MEASURE_NAME_RU")
	private String measureNameRu;
	
	@Column(name="MEASURE_NAME_EN")
	private String measureNameEn;

	@Size(max = 1)
	@Column(name = "PRESENT_IN_CHARGE", length = 1)
	private String presentInCharge;

	@Size(max = 1)
	@Column(name = "PRESENT_IN_MAIN_SCREEN", length = 1)
	private String presentInMainScreen;

	public String getNameByLang(Lang lang) {
		switch (lang) {
		case RU:
			return this.nameRu;
		case EN:
			return this.nameEn;
		default:
			return this.nameRu;
		}
	}
	
	public String getMeasureNameByLang(Lang lang) {
		switch (lang) {
		case RU:
			return this.measureNameRu;
		case EN:
			return this.measureNameEn;
		default:
			return this.measureNameRu;
		}
	}
}
