package by.com.lifetech.billingapi.models.entity.dictionary;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberStateId implements Serializable {

	private static final long serialVersionUID = -4631209921021209214L;

	private String code;
	private String reason;
}
