package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DICT_SUBSCRIBER_SEGMENT", schema = "conf")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberSegment implements CodeNameDictionary {
	@Id
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
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
