package by.com.lifetech.billingapi.models.entity.dictionary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import by.com.lifetech.billingapi.models.entity.OmWebAttribute;
import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "WEB_ATTR", schema = "tm_om")
@EqualsAndHashCode(exclude = {"attributes"})
public class DictWebAttribute implements CodeNameDictionary {
	@Id
	@Column(name="CODE_ATTRIBUTES")
	private String code;
	
	@Column(name="INDEX_ATTR")
	private String index;
	
	@Column(name="NAME_RU")
	private String nameRu;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "attrCode")
	private List<OmWebAttribute> attributes;
	
	public String getNameByLang(Lang lang) {
        return this.nameRu;
	}
}
