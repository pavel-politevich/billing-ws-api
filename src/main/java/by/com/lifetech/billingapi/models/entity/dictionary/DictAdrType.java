package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_ADR_TYPE", schema = "conf")
public class DictAdrType implements CodeNameDictionary {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME_EN")
    private String nameEn;

    @Column(name = "NAME_UA")
    private String nameUa;

    @Column(name = "NAME_RU")
    private String nameRu;

    @Column(name = "ABBR_EN")
    private String abbrEn;

    @Column(name = "ABBR_UA")
    private String abbrUa;

    @Column(name = "ABBR_RU")
    private String abbrRu;

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