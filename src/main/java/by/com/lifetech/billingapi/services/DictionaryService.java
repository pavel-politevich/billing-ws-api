package by.com.lifetech.billingapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.dictionary.ChannelLocalizations;
import by.com.lifetech.billingapi.models.entity.dictionary.CodeNameDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.UniversalDictionaryImpl;
import by.com.lifetech.billingapi.models.enums.DictName;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.dictionary.ChannelLocalizationsRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.ContractTypeRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.LevelRiskFraudRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.RegistrationTypeRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.SubscriberSegmentRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.SubscriberStateRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.SubscriptionTypeRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.TransactionTypeRepository;

@Service
public class DictionaryService {

	@Autowired
	private ChannelLocalizationsRepository channelLocalizationsRepository;

	@Autowired
	private ContractTypeRepository contractTypeRepository;
	@Autowired
	private SubscriberStateRepository subscriberStateRepository;
	@Autowired
	private SubscriberSegmentRepository subscriberSegmentRepository;
	@Autowired
	private LevelRiskFraudRepository levelRiskFraudRepository;
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	@Autowired
	private RegistrationTypeRepository registrationTypeRepository;
	@Autowired
	private SubscriptionTypeRepository subscriptionTypeRepository;

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
	
	public UniversalDictionaryImpl getChannelLocalizationDict(String code, Lang lang) {
		
		Optional<ChannelLocalizations> channelLocOp = channelLocalizationsRepository
				.findChannelLocalizations(code, defaultChannel).stream()
				.filter(chLoc -> chLoc.getLangCode().equals(lang.name())).findFirst();
		if (channelLocOp.isPresent()) {
			return new UniversalDictionaryImpl(code, channelLocOp.get().getValue());
		} else {
			return null;
		}
	}


	public ServiceResponseDto<List<UniversalDictionaryImpl>> getDictByName(DictName dictName, String value, Lang lang) {

		List<UniversalDictionaryImpl> resultList = new ArrayList<UniversalDictionaryImpl>();
		ServiceResponseDto<List<UniversalDictionaryImpl>> serviceResponse = new ServiceResponseDto<List<UniversalDictionaryImpl>>();
		
		if (dictName == DictName.DICT_TARIFF) {
			channelLocalizationsRepository.findProductByName(value, defaultChannel, "TARIFF")
					.forEach(p -> resultList.add(new UniversalDictionaryImpl(p.getCode(), p.getName())));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}
		
		if (dictName == DictName.DICT_CATEGORY) {
			channelLocalizationsRepository.findProductByName(value, defaultChannel, "STARTER_PACK")
					.forEach(p -> resultList.add(new UniversalDictionaryImpl(p.getCode(), p.getName())));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}

		List<? extends CodeNameDictionary> dbList = new ArrayList<CodeNameDictionary>();
		dbList = switch (dictName) {
		case DICT_CONTRACT_TYPE -> contractTypeRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(value, value);
		case DICT_STATE -> subscriberStateRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(value, value);
		case DICT_SEGMENT -> subscriberSegmentRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(value, value);
		case DICT_RISK_LEVEL -> levelRiskFraudRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(value, value);
		case DICT_LINE_LEVEL -> subscriptionTypeRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(value, value);
		case DICT_TH_TYPE -> transactionTypeRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(value, value);
		case DICT_REG_TYPE -> registrationTypeRepository.findByNameRuContainsIgnoreCaseOrNameEnContainsIgnoreCase(value, value);
		default -> null;
		};

		dbList.forEach(p -> resultList.add(new UniversalDictionaryImpl(p.getCode(), p.getNameByLang(lang))));
		serviceResponse.setDefaultSuccessResponse();
		serviceResponse.setResultMap(resultList);
		return serviceResponse;
	}

}
