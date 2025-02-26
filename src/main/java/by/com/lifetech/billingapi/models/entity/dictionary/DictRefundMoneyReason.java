package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DICT_CRM_REFUND_REASON", schema = "conf")
public class DictRefundMoneyReason extends AbstracDictionary implements CodeNameDictionary {
}