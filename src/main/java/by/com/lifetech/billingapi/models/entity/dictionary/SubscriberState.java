package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
//@IdClass(SubscriberStateId.class)
@Table(name="DICT_SUBSCRIBER_STATE", schema = "conf")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberState extends AbstracDictionary implements CodeNameDictionary {
	//@Id
	@Column(name="REASON_CODE")
	private String reason;
	
}
