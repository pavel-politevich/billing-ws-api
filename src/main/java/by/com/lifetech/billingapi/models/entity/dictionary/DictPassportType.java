package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_PASSPORT_TYPE_V", schema = "CONF")
public class DictPassportType extends AbstracDictionary implements CodeNameDictionary {
}