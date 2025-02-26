package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_COMMUNITIES_V", schema = "conf")
public class DictCommunities extends AbstracDictionary implements CodeNameDictionary {
}