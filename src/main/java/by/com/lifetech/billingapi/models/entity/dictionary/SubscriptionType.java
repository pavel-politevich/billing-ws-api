package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TM_SUBSCRIPTION_TYPE", schema = "conf")
public class SubscriptionType extends AbstracDictionary implements CodeNameDictionary {

}
