package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_SIM_CHANGE", schema = "CONF")
public class DictSimChange extends AbstracDictionary implements CodeNameDictionary {
    @Column(name="COMP_NAME")
    private String compName;
    @Column(name="TH_CODE")
    private Long thCode;
    @Column(name="TYPE")
    private String type;
    @Column(name="CNTTOP_ID")
    private String cntTopId;
}