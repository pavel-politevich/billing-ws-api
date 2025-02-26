package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DICT_ERROR_MESSAGE", schema = "CONF")
public class DictErrorMessage extends AbstracDictionary implements CodeNameDictionary {

}
