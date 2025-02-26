package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "GROUPS", schema = "iui")
public class DictServiceGroup implements CodeNameDictionary {

	@Id
	@Column(name="CODE")
	private int code;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Column(name="SORT_ORDER")
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
	
	public String getCode() {
		return String.valueOf(code);
	}

	@JsonGetter("code")
	public int getCodeOriginal() {
		return code;
	}
}
