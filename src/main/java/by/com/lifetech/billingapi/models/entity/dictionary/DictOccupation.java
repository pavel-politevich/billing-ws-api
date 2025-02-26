package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.enums.Lang;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_OCCUPATION", schema = "conf")
public class DictOccupation implements CodeNameDictionary {
    @Id
    @Size(max = 15)
    @Column(name = "CODE", nullable = false, length = 15)
    private String code;

    @Size(max = 256)
    @NotNull
    @Column(name = "NAME_RU", nullable = false, length = 256)
    private String nameRu;

    @Column(name = "PRIORITY")
    private Long priority;

    @Size(max = 255)
    @Column(name = "NAME_EN")
    private String nameEn;

    @Size(max = 255)
    @Column(name = "NAME_BY")
    private String nameBy;

    @Column(name = "ACT")
    private Long act;

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