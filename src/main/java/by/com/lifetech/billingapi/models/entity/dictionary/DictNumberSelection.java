package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_NUMBER_SELECTION", schema = "CONF")
public class DictNumberSelection implements CodeNameDictionary {
    @Id
    @Column(name="SS_CODE")
    private String code;
    @Column(name="OM_CODE")
    private String omCode;
    @Column(name="NAME")
    private String name;

    @Override
    public String getNameByLang(Lang lang) {
        return name;
    }
}