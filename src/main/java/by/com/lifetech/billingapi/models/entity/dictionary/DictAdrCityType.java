package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "DICT_ADR_CITY_TYPE", schema = "conf")
public class DictAdrCityType implements CodeNameDictionary, Serializable {
    @Serial
    private static final long serialVersionUID = 4616471626747649024L;

    @Id
    @Size(max = 16)
    @Column(name = "CODE", nullable = false, length = 16)
    private String code;

    @Size(max = 64)
    @NotNull
    @Column(name = "NAME_EN", nullable = false, length = 64)
    private String nameEn;

    @Size(max = 128)
    @Column(name = "NAME_UA", length = 128)
    private String nameUa;

    @Size(max = 128)
    @Column(name = "NAME_RU", length = 128)
    private String nameRu;

    @Size(max = 64)
    @NotNull
    @Column(name = "ABBR_EN", nullable = false, length = 64)
    private String abbrEn;

    @Size(max = 128)
    @Column(name = "ABBR_UA", length = 128)
    private String abbrUa;

    @Size(max = 128)
    @Column(name = "ABBR_RU", length = 128)
    private String abbrRu;

    @JsonIgnore
    @OneToMany(mappedBy = "cityTypeCode")
    private Set<DictAdrCity> dictAdrCities = new LinkedHashSet<>();

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