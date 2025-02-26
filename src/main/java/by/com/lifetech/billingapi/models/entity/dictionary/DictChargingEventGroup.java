package by.com.lifetech.billingapi.models.entity.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_CHARGING_EVENT_GROUP", schema = "conf")
public class DictChargingEventGroup extends AbstracDictionary implements CodeNameDictionary {

    @Column(name="PARENT_CODE")
    private String parentCode;

    @JsonIgnore
    @Column(name="ORDER_NO")
    private String orderNo;
}
