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
@Table(name = "DICT_BANKS", schema = "tm_fm")
public class DictBanks implements CodeNameDictionary {

    @Id
    @Column(name="CODE")
    private String code;

    @Column(name="NAME")
    private String name;

    @Column(name="IS_DISABLED")
    private String isDisabled;

    @Column(name="BANK_COMMISSION")
    private Double bankCommission;

    @Column(name="BANK_ACCOUNT")
    private String bankAccount;

    @Override
    public String getNameByLang(Lang lang) {
        return name;
    }
}