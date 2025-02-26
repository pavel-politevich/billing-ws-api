package by.com.lifetech.billingapi.models.entity.dictionary;

import by.com.lifetech.billingapi.models.dto.UniversalDictionary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedNativeQuery(
		name = "ChannelLocalizations.findProductByName",
		query = """
					select tr.product_code as code, t.value as name from tm_om.om_channel_localizations t
					join tm_om.om_channel_registry tr on t.registry_code = tr.code
					where upper(t.value) like upper('%' || :name || '%') and tr.rc_type_code = :channelCode
					and tr.product_code in
					(
					  select p.code from tm_om.om_product_offerings p
					  where p.product_srecification_type = :specType
					)
			""",
		resultSetMapping = "UniversalDictionary"
)

@NamedNativeQuery(
		name = "ChannelLocalizations.findProductByCode",
		query = """
					select tr.product_code as code, t.value as name from tm_om.om_channel_localizations t
					join tm_om.om_channel_registry tr on t.registry_code = tr.code
					where tr.product_code = :code and tr.rc_type_code = :channelCode
					and upper(t.rc_lang_code) = upper(:lang)
					and tr.product_code in
					(
					  select p.code from tm_om.om_product_offerings p
					  where p.product_srecification_type = :specType
					)
			""",
		resultSetMapping = "UniversalDictionary"
)

@SqlResultSetMapping(
		name = "UniversalDictionary",
		classes = {
				@ConstructorResult(
						targetClass = UniversalDictionary.class,
						columns = {
								@ColumnResult(name = "code", type = String.class),
								@ColumnResult(name = "name", type = String.class)
						}
				)
		}
)

@Entity
@IdClass(ChannelLocalizationsId.class)
@Table(name = "OM_CHANNEL_LOCALIZATIONS", schema = "tm_om")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelLocalizations {

	@Id
	@Column(name = "REGISTRY_CODE")
	private Long registryCode;

	@Id
	@Column(name = "RC_FNAME_CODE")
	private String channelCode;
	
	@Id
	@Column(name = "RC_LANG_CODE")
	private String langCode;
	
	@Column(name = "VALUE")
	private String value;
}
