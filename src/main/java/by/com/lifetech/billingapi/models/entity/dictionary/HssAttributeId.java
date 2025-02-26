package by.com.lifetech.billingapi.models.entity.dictionary;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HssAttributeId implements Serializable {

	private static final long serialVersionUID = -2000571623001317261L;
	private String code;
	private String groupCode;
}
