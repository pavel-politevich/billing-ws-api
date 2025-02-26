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
@Table(name = "DICT_INFORMATION_SOURCE", schema = "conf")
public class DictInformationSource implements CodeNameDictionary {
    @Id
    @Size(max = 512)
    @Column(name = "CODE", nullable = false, length = 512)
    private String code;

    @Size(max = 1024)
    @Column(name = "NAME_EN", length = 1024)
    private String nameEn;

    @Size(max = 1024)
    @Column(name = "NAME_UA", length = 1024)
    private String nameUa;

    @Size(max = 1024)
    @Column(name = "NAME_RU", length = 1024)
    private String nameRu;

    @Size(max = 1)
    @Column(name = "IS_VISIBLE", length = 1)
    private String isVisible;

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