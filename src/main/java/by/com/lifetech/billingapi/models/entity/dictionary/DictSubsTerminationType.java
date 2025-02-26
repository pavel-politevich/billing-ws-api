package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_SUBSCRIBER_DEACT_REASON", schema = "conf")
public class DictSubsTerminationType extends AbstracDictionary implements CodeNameDictionary{
    @Column(name="FN_GROUPS")
    private String fnGroups;
}
