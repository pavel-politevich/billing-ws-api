package by.com.lifetech.billingapi.services;

import java.util.*;
import java.util.function.Predicate;

import by.com.lifetech.billingapi.models.dto.UniversalDictionary;
import by.com.lifetech.billingapi.models.entity.dictionary.*;
import by.com.lifetech.billingapi.models.enums.DictSearchType;
import by.com.lifetech.billingapi.models.enums.FullDictName;
import by.com.lifetech.billingapi.models.repository.dictionary.*;
import by.life.crmadvancedsearch.builder.SpecificationBuilder;
import by.life.crmadvancedsearch.model.SearchCriterion;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.DictName;
import by.com.lifetech.billingapi.models.enums.Lang;

@Service
@RequiredArgsConstructor
public class DictionaryService {
	private final ChannelLocalizationsRepository channelLocalizationsRepository;
	private final ContractTypeRepository contractTypeRepository;
	private final SubscriberStateRepository subscriberStateRepository;
	private final SubscriberSegmentRepository subscriberSegmentRepository;
	private final LevelRiskFraudRepository levelRiskFraudRepository;
	private final TransactionTypeRepository transactionTypeRepository;
	private final RegistrationTypeRepository registrationTypeRepository;
	private final SubscriptionTypeRepository subscriptionTypeRepository;
	private final PcrfAttrRepository pcrfAttrRepository;
	private final HssAttrRepository hssAttrRepository;
	private final ServiceSubGroupRepository serviceSubGroupRepository;
	private final ServiceGroupRepository serviceGroupRepository;
	private final ChargingEventGroupRepository chargingEventGroupRepository;
	private final DictErrorMessageRepository dictErrorMessageRepository;
	private final DictBanksRepository dictBanksRepository;
	private final DictFinancialOperationRepository dictFinOperationRepository;
	private final DictSubsTermTypeRepository dictSubsTermTypeRepository;
	private final DictRefundMoneyReasonRepository refundMoneyReasonRepository;
	private final DictSimChangeRepository dictSimChangeRepository;
	private final DictOccupationRepository occupationRepository;
	private final DictInformationSourceRepository informationSourceRepository;
	private final DictAdrCountryRepository countryRepository;
	private final DictPassportTypeRepository passportTypeRepository;
	private final DictNumberSelectionRepository numberSelectionRepository;
	private final DictAdrCityRepository dictAdrCityRepository;
	private final DictAdrDistrictRepository dictAdrDistrictRepository;
	private final DictAdrCityTypeRepository dictAdrCityTypeRepository;
	private final DictAdrRegionRepository dictAdrRegionRepository;
	private final DictAdrStreetTypeRepository dictAdrStreetTypeRepository;
	private final DictAdrTypeRepository dictAdrTypeRepository;

    Logger logger = LoggerFactory.getLogger(DictionaryService.class);

	@Value("${app.default.channel}")
	private String defaultChannel;

	public String getChannelLocalName(String productCode, Lang lang) {

		return getLocalNameByChannel(productCode, defaultChannel, lang);
	}

	public String getLocalNameByChannel(String productCode, String channel, Lang lang) {
		logger.debug("productCode= {}, locale= {}", productCode, lang.name());
		if (channel == null || channel.isEmpty()) {
			channel = defaultChannel;
		}
		Optional<ChannelLocalizations> channelLocOp = channelLocalizationsRepository
				.findChannelLocalizations(productCode, channel).stream()
				.filter(chLoc -> chLoc.getLangCode().equals(lang.name())).findFirst();
		logger.debug("Localization name for poduct {} is {}", productCode, channelLocOp);
		return channelLocOp.map(ChannelLocalizations::getValue).orElse(null);
	}

	public UniversalDictionary getChannelLocalizationDict(String code, Lang lang) {
		
		Optional<ChannelLocalizations> channelLocOp = channelLocalizationsRepository
				.findChannelLocalizations(code, defaultChannel).stream()
				.filter(chLoc -> chLoc.getLangCode().equals(lang.name())).findFirst();
        return channelLocOp.map(channelLocalizations -> new UniversalDictionary(code, channelLocalizations.getValue())).orElse(null);
	}

	public ServiceResponseDto<List<UniversalDictionary>> getDictBySearchType(DictName dictName, DictSearchType searchType, String value, Lang lang) {

		List<UniversalDictionary> resultList = new ArrayList<>();
		ServiceResponseDto<List<UniversalDictionary>> serviceResponse = new ServiceResponseDto<>();

		Predicate<? super CodeNameDictionary> filterPredicate;
		filterPredicate = switch (searchType) {
            case CONTAINS_BY_VALUE -> d -> d != null && Optional.ofNullable(d.getNameByLang(lang)).orElse("").toLowerCase().contains(value.toLowerCase());
            case EQUALS_BY_CODE -> d -> d != null && d.getCode().equals(value);
		};

		if (dictName == DictName.DICT_TARIFF && searchType == DictSearchType.EQUALS_BY_CODE) {
            resultList.addAll(channelLocalizationsRepository.findProductByCode(value, defaultChannel, "TARIFF", lang.name()));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}
		if (dictName == DictName.DICT_CATEGORY && searchType == DictSearchType.EQUALS_BY_CODE) {
            resultList.addAll(channelLocalizationsRepository.findProductByCode(value, defaultChannel, "STARTER_PACK", lang.name()));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}
		if (dictName == DictName.DICT_SERVICE_NAME && searchType == DictSearchType.EQUALS_BY_CODE) {
            resultList.addAll(channelLocalizationsRepository.findProductByCode(value, defaultChannel, "SERVICE", lang.name()));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}
		if (dictName == DictName.DICT_TARIFF) {
            resultList.addAll(channelLocalizationsRepository.findProductByName(value, defaultChannel, "TARIFF"));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}
		if (dictName == DictName.DICT_CATEGORY) {
            resultList.addAll(channelLocalizationsRepository.findProductByName(value, defaultChannel, "STARTER_PACK"));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}
		if (dictName == DictName.DICT_SERVICE_NAME) {
            resultList.addAll(channelLocalizationsRepository.findProductByName(value, defaultChannel, "SERVICE"));
			serviceResponse.setDefaultSuccessResponse();
			serviceResponse.setResultMap(resultList);
			return serviceResponse;
		}

		List<? extends CodeNameDictionary> dbList;
		dbList = switch (dictName) {
			case DICT_CONTRACT_TYPE -> contractTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_STATE -> subscriberStateRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_SEGMENT -> subscriberSegmentRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_RISK_LEVEL -> levelRiskFraudRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_LINE_LEVEL -> subscriptionTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_TH_TYPE -> transactionTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_REG_TYPE -> registrationTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_PCRF_ATTR -> pcrfAttrRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_HSS_ATTR -> hssAttrRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_SERVICE_SUBGROUP -> serviceSubGroupRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_SERVICE_GROUP -> serviceGroupRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_CHARGE_EVENT_GROUP -> chargingEventGroupRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_ERROR_CODE -> dictErrorMessageRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_BANKS -> dictBanksRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_MP_FIN_OPERATION -> dictFinOperationRepository.getForManualPayment().stream().filter(
					filterPredicate
			).toList();
			case DICT_MC_FIN_OP_IND -> dictFinOperationRepository.getForManualCorrection("2").stream().filter(
					filterPredicate
			).toList();
			case DICT_MC_FIN_OP_CORP -> dictFinOperationRepository.getForManualCorrection("1").stream().filter(
					filterPredicate
			).toList();
			case DICT_SUBS_TERM_TYPE -> dictSubsTermTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_REFUND_MONEY_REASON -> refundMoneyReasonRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_SIM_CHANGE -> dictSimChangeRepository.findByTypeIn(List.of("SUBSCRIBER","BOTH")).stream().filter(
					filterPredicate
			).toList();
			case DICT_SIM_CHANGE_DEALER -> dictSimChangeRepository.findByTypeIn(List.of("DEALER","BOTH")).stream().filter(
					filterPredicate
			).toList();
			case DICT_OCCUPATION -> occupationRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_INFORMATION_SOURCE -> informationSourceRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_ADR_COUNTRY -> countryRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_PASSPORT_TYPE -> passportTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_NUMBER_SELECTION -> numberSelectionRepository.findAllByLang(defaultChannel, lang.name()).stream().filter(
					filterPredicate
			).toList();
			case DICT_ADR_CITY -> dictAdrCityRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_ADR_DISTRICT -> dictAdrDistrictRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_ADR_CITY_TYPE -> dictAdrCityTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_ADR_REGION -> dictAdrRegionRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_ADR_STREET_TYPE -> dictAdrStreetTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			case DICT_ADR_TYPE -> dictAdrTypeRepository.findAll().stream().filter(
					filterPredicate
			).toList();
			default -> new ArrayList<>();
		};

		dbList.forEach(p -> {
			if (p != null) {
				resultList.add(new UniversalDictionary(p.getCode(), p.getNameByLang(lang)));
			}
		});

		serviceResponse.setDefaultSuccessResponse();
		serviceResponse.setResultMap(resultList);
		return serviceResponse;
	}

	public ServiceResponseDto<List<?>> getFullDictionary(FullDictName dictName, List<SearchCriterion> searchCriteria) {
		List<?> dbList;
		dbList = switch (dictName) {
			case DICT_FINANCIAL_OPERATION -> {
				Specification<DictFinancialOperation> spec = new SpecificationBuilder<DictFinancialOperation>().build(searchCriteria);
				yield dictFinOperationRepository.findAll(spec).stream().toList();
			}
			default -> new ArrayList<>();
		};
		ServiceResponseDto<List<?>> serviceResponse = new ServiceResponseDto<>();
		serviceResponse.setDefaultSuccessResponse();
		serviceResponse.setResultMap(dbList);
		return serviceResponse;
	}

}
