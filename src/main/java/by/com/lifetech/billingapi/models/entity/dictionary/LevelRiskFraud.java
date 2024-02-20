package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DICT_LEVEL_RISK_FRAUD", schema = "conf")

public class LevelRiskFraud extends AbstracDictionary implements CodeNameDictionary {

}
