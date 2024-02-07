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
@Table(name="TRANSACTION_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionType implements CodeNameDictionary {
	
	@Id
	@Column(name="CODE")
	private Long code;
	
	@Column(name="PARENT_TYPE_CODE")
	private Long parentTypeCode;
	
	@Column(name="NAME_EN")
	private String nameEn;
	
	@Column(name="NAME_UA")
	private String nameUa;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
	@Column(name="N_MSG_CODE")
	private String nMsgCode;
	
	@Column(name="O_FILTER")
	private String oFilter;
	
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
