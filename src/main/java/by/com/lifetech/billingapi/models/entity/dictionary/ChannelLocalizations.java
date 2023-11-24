package by.com.lifetech.billingapi.models.entity.dictionary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
