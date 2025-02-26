package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_SUBSCRIBER_RISK_FRAUD", schema = "conf")
public class DictSubsRiskFraud extends AbstracDictionary implements CodeNameDictionary {
}