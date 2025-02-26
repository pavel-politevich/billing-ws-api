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
@Table(name = "DICT_ADR_COUNTRY", schema = "conf")
public class DictAdrCountry  implements CodeNameDictionary, Serializable {
    @Serial
    private static final long serialVersionUID = -763341287157873576L;

    @Id
    @Size(max = 16)
    @Column(name = "CODE", nullable = false, length = 16)
    private String code;

    @Size(max = 128)
    @NotNull
    @Column(name = "NAME_EN", nullable = false, length = 128)
    private String nameEn;

    @Size(max = 256)
    @Column(name = "NAME_UA", length = 256)
    private String nameUa;

    @Size(max = 256)
    @Column(name = "NAME_RU", length = 256)
    private String nameRu;

    @Column(name = "IS_VISIBLE")
    private Boolean isVisible;

    @Column(name = "ORDER_NO")
    private Short orderNo;

    @JsonIgnore
    @OneToMany(mappedBy = "countryCode")
    private Set<DictAdrRegion> dictAdrRegions = new LinkedHashSet<>();

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