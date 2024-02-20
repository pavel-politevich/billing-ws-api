package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DICT_CONTRACT_TYPE", schema = "conf")
public class ContractType extends AbstracDictionary implements CodeNameDictionary {

}
