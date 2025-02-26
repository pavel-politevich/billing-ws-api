package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "DICT_ADR_DISTRICT", schema = "conf")
public class DictAdrDistrict implements CodeNameDictionary, Serializable {
    @Serial
    private static final long serialVersionUID = 327117167061327099L;

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

    @Size(max = 1)
    @Column(name = "IS_DEFAULT", length = 1)
    private String isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "REGION_CODE")
    private DictAdrRegion regionCode;

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