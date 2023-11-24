package by.com.lifetech.billingapi.models.entity.dictionary;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelLocalizationsId implements Serializable {

	private static final long serialVersionUID = 1569182823640987956L;
	
	private Long registryCode;
	private String channelCode;
	private String langCode;

}
