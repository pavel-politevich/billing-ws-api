package by.com.lifetech.billingapi.models.entity.dictionary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dictionary with code and name")
public class UniversalDictionaryImpl implements UniversalDictionary {
	@Schema(description = "dict code", example = "CORP")
	private String code;
	@Schema(description = "dict name by lang code", example = "Corporate")
	private String name;
}
