package by.com.lifetech.billingapi.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.models.entity.dictionary.ChannelLocalizations;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.dictionary.ChannelLocalizationsRepository;

@Service
public class DictionaryService {
	
	@Autowired
	private ChannelLocalizationsRepository channelLocalizationsRepository;

	Logger logger = LoggerFactory.getLogger(DictionaryService.class);

	@Value("${app.default.channel}")
	private String defaultChannel;
	
	
	public String getChannelLocalName(String productCode, Lang lang) {

		logger.debug("productCode= {}, locale= {}, find= {}", productCode, lang.name(),
				channelLocalizationsRepository.findChannelLocalizations(productCode, defaultChannel).toString());

		Optional<ChannelLocalizations> channelLocOp = channelLocalizationsRepository
				.findChannelLocalizations(productCode, defaultChannel).stream()
				.filter(chLoc -> chLoc.getLangCode().equals(lang.name())).findFirst();

		logger.debug("Localization name for poduct {} is {}", productCode, channelLocOp);

		if (channelLocOp.isPresent()) {
			return channelLocOp.get().getValue();
		} else {
			return null;
		}
	}

}
