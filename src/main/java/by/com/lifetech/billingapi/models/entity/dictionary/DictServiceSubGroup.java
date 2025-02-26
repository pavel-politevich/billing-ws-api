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
@Table(name = "SUBGROUPS", schema = "iui")
public class DictServiceSubGroup implements CodeNameDictionary {
	
	@Id
	@Column(name="IUI_GROUP_NAME")
	private String code;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Column(name="SORT_ORDER")
	private int sort;
	
	@Column(name="IUI_TAB_CODE")
	private int groupId;
	
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
