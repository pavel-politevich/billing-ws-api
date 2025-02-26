package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dict_measure_units", schema = "conf")
public class DictMeasureUnit extends AbstracDictionary implements CodeNameDictionary {

}
