package by.com.lifetech.billingapi.models.entity.dictionary;

import java.util.Objects;

import by.com.lifetech.billingapi.models.enums.Lang;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(SubscriberStateId.class)
@Table(name = "DICT_SUBSCRIBER_STATE", schema = "conf")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberState extends AbstracDictionary implements CodeNameDictionary {

	@Id
	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME_EN")
	private String nameEn;

	@Column(name = "NAME_RU")
	private String nameRu;

	@Id
	@Column(name = "REASON_CODE")
	private String reason;

	public String getCode() {
		if (this.reason != null) {
			return this.code + '/' + this.reason;
		} else {
			return this.code;
		}
	}

	@JsonGetter("code")
	public String getCodeOriginal() {
		return code;
	}
	
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriberState other = (SubscriberState) obj;
		return Objects.equals(code, other.code) && Objects.equals(reason, other.reason);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, reason);
	}

}
