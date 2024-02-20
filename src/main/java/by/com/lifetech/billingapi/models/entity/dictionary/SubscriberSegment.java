package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DICT_SUBSCRIBER_SEGMENT", schema = "conf")

public class SubscriberSegment extends AbstracDictionary implements CodeNameDictionary {

}
