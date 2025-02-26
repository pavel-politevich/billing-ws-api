package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "DICT_HLR_ATTRIBUTE_GROUP", schema = "conf")
public class HssAttributuGroup implements CodeNameDictionary {
	
	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Column(name="SORT_PRIORITY")
	private int sort;
	
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

}
