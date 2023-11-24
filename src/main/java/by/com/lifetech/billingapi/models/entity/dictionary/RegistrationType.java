package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DICT_REGISTRATION_TYPE", schema = "conf")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationType {
	@Id
	@Column(name="REGISTRATION_TYPE_CODE")
	private String registrationTypeCode;
	
	@Column(name="REGISTRATION_TYPE_NAME_EN")
	private String nameEn;
	
	@Column(name="REGISTRATION_TYPE_NAME_RU")
	private String nameRu;
	
	@Column(name="DESCRIPTION")
	private String description;
}
