package by.com.lifetech.billingapi.models.entity;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeId implements Serializable {

	@Serial
	private static final long serialVersionUID = -4848413025254546636L;
	private ProductOffering productOffering;
	private String code;
}
