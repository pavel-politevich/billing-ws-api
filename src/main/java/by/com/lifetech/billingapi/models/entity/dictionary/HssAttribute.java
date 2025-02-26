package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@IdClass(HssAttributeId.class)
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "DICT_HLR_ATTRIBUTE", schema = "conf")
public class HssAttribute implements CodeNameDictionary {

	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Column(name="SORT_PRIORITY")
	private int sort;
	
	@Id
	@Column(name="GROUP_CODE")
	private String groupCode;
	
	@Column(name="SEARCH_GROUP_CODE")
	private String searchGroupCode;
	
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
