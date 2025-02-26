package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_ADR_STREET_TYPE", schema = "conf")
public class DictAdrStreetType implements CodeNameDictionary {
    @Id
    @Size(max = 1024)
    @Column(name = "CODE", nullable = false, length = 1024)
    private String code;

    @Size(max = 2048)
    @Column(name = "NAME_UA", length = 2048)
    private String nameUa;

    @Size(max = 2048)
    @Column(name = "NAME_RU", length = 2048)
    private String nameRu;

    @Size(max = 2048)
    @Column(name = "NAME_EN", length = 2048)
    private String nameEn;

    @Override
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