package by.com.lifetech.billingapi.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.CategoryDto;
import by.com.lifetech.billingapi.models.dto.RegHistoryRequestDto;
import by.com.lifetech.billingapi.models.dto.RegistrationTypeDto;
import by.com.lifetech.billingapi.models.dto.SubscriberInfDto;
import by.com.lifetech.billingapi.models.dto.THistoryRequestDto;
import by.com.lifetech.billingapi.models.dto.TariffDto;
import by.com.lifetech.billingapi.models.dto.TransactionTypeDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.RegistrationHistory;
import by.com.lifetech.billingapi.models.entity.TransactionHistory;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.RegistrationHistoryRepository;
import by.com.lifetech.billingapi.models.repository.TransactionHistoryRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.ContractTypeRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.LevelRiskFraudRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.RegistrationTypeRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.SubscriberSegmentRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.SubscriberStateRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.SubscriptionTypeRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.TransactionTypeRepository;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.com.lifetech.billingapi.wsdl.ResultCodeType;

@Service
public class SubscriberInfoService {

	@Autowired
	private RegistrationHistoryRepository registrationHistoryRepository;

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;

	@Autowired
	private ContractTypeRepository contractTypeRepository;

	@Autowired
	private SubscriberSegmentRepository subscriberSegmentRepository;

	@Autowired
	private SubscriberStateRepository subscriberStateRepository;

	@Autowired
	private LevelRiskFraudRepository levelRiskFraudRepository;

	@Autowired
	private SubscriptionTypeRepository subscriptionTypeRepository;

	@Autowired
	private RegistrationTypeRepository registrationTypeRepository;

	@Autowired
	private TransactionTypeRepository transactionTypeRepository;

	@Autowired
	ChainService chainService;

	@Autowired
	DictionaryService dictionaryService;

	Logger logger = LoggerFactory.getLogger(SubscriberInfoService.class);

	@Value("${app.default.locale}")
	private String defaultLocale;

	@Value("${app.default.channel}")
	private String defaultChannel;

	final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ServiceResponseDto<Page<RegistrationHistory>> getRegistrationHistory(RegHistoryRequestDto req, Pageable page)
			throws BusinessException {

		logger.info("Start calling getRegistrationHistory with: {}", req.toString());

		LocalDateTime startDt;
		LocalDateTime endDt;
		Lang lang = Lang.getByValue(req.getLanguage());
		Page<RegistrationHistory> regHistories;

		if (req.getDateFrom() != null) {
			startDt = req.getDateFrom();
		} else
			startDt = LocalDateTime.now().minusDays(1L);

		if (req.getDateTo() != null) {
			endDt = req.getDateTo();
		} else
			endDt = LocalDateTime.now();
		
		regHistories = registrationHistoryRepository.findAllByFields(startDt.format(CUSTOM_FORMATTER),
				endDt.format(CUSTOM_FORMATTER), req.getSalesPoint(), req.getTariff().getCode(), req.getAgent(),
				req.getCategory().getCode(), req.getRegistrationType().getCode(), req.getContractCode(), page);

		for (RegistrationHistory rh : regHistories) {

			TariffDto tariff = new TariffDto(rh.getTariff(),
					dictionaryService.getChannelLocalName(rh.getTariff(), lang));
			CategoryDto category = new CategoryDto(rh.getCategory(),
					dictionaryService.getChannelLocalName(rh.getCategory(), lang));
			RegistrationTypeDto regType = new RegistrationTypeDto(rh.getRegistrationType(), "");

			registrationTypeRepository.findByRegistrationTypeCode(rh.getRegistrationType())
					.ifPresent(name -> regType.setName(name.getNameByLang(lang)));

			rh.setTariffDto(tariff);
			rh.setCategoryDto(category);
			rh.setRegistrationTypeDto(regType);
		}

		ServiceResponseDto<Page<RegistrationHistory>> serviceResponse = new ServiceResponseDto<Page<RegistrationHistory>>();
		serviceResponse.setDefaultSuccessResponse();
		serviceResponse.setResultMap(regHistories);
		
		return serviceResponse;
	}

	public ServiceResponseDto<Page<TransactionHistory>> getTransactionHistory(THistoryRequestDto req, Pageable page)
			throws BusinessException {

		logger.info("Start calling getTransactionHistory with: {}", req.toString());

		if (req.getDateFrom() == null || req.getDateTo() == null || req.getDateFrom().isAfter(req.getDateTo())) {
			throw new BusinessException("Invalid date_from or date_to");
		}

		Lang lang = Lang.getByValue(req.getLanguage());

		Page<TransactionHistory> thList = transactionHistoryRepository.findAllByFields(
				req.getDateFrom().format(CUSTOM_FORMATTER), req.getDateTo().format(CUSTOM_FORMATTER), req.getMsisdn(),
				req.getContractCode(), req.getAgent(), req.getTransactionType().getCode(), page);

		for (TransactionHistory th : thList) {
			TransactionTypeDto thDto = new TransactionTypeDto(th.getTransactionTypeCode(), "");

			transactionTypeRepository.findByCode(th.getTransactionTypeCode())
					.ifPresent(name -> thDto.setName(name.getNameByLang(lang)));
			th.setTransactionType(thDto);
		}

		logger.info("Stop calling getTransactionHistory");
		
		ServiceResponseDto<Page<TransactionHistory>> serviceResponse = new ServiceResponseDto<Page<TransactionHistory>>();
		serviceResponse.setDefaultSuccessResponse();
		serviceResponse.setResultMap(thList);
		
		return serviceResponse;
	}

	public ServiceResponseDto<SubscriberInfDto> getSubscriberInf(String msisdn, String locale) throws BusinessException {

		logger.info("Start calling getSubscriberInf with msisdn: {}", msisdn);

		Lang lang = Lang.getByValue(locale);
		Map<String, Object> map = new HashMap<>();
		map.put("msisdn", msisdn);
		ChainResult chainResult = chainService.executeChain(ChainType.CIM, "getSubscriberInf", map);

		if (!chainResult.getResultCode().equals(ResultCodeType.SUCCESS)) {
			throw new BusinessException("Billing chain service error");
		}

		SubscriberInfDto subscriberInfDto = new SubscriberInfDto(chainResult.getResultList());
		subscriberInfDto.setMsisdn(msisdn);

		map.clear();
		map.put("MSISDN", msisdn);
		map.put("TypeOfPayment", "3");

		chainResult = chainService.executeChain(ChainType.FM, "ServiceInfoChecking", map);

		if (!chainResult.getResultCode().equals(ResultCodeType.SUCCESS)) {
			logger.warn("Error while getting rec sum for msisdn: {}", msisdn);
		}

		chainResult.getResultList().stream().filter(el -> el.getName().equals("Debt")).findFirst()
				.ifPresent(v -> subscriberInfDto.setRecAmount(Double.parseDouble(v.getValue().toString())));

		subscriberInfDto.getTariff()
				.setName(dictionaryService.getChannelLocalName(subscriberInfDto.getTariff().getCode(), lang));

		contractTypeRepository.findByCode(subscriberInfDto.getContractType().getCode())
				.ifPresent(name -> subscriberInfDto.getContractType().setName(name.getNameByLang(lang)));

		subscriberSegmentRepository.findByCode(subscriberInfDto.getSegment().getCode())
				.ifPresent(name -> subscriberInfDto.getSegment().setName(name.getNameByLang(lang)));

		String state = subscriberInfDto.getState().getCode();
		String stateCode = state.substring(0, state.indexOf("/", 5));
		String reasonCode = state.substring(state.indexOf('/', 5) + 1);

		subscriberStateRepository.findByCodeAndReason(stateCode, reasonCode)
				.ifPresent(name -> subscriberInfDto.getState().setName(name.getNameByLang(lang)));

		levelRiskFraudRepository.findByCode(subscriberInfDto.getRiskLevel().getCode())
				.ifPresent(name -> subscriberInfDto.getRiskLevel().setName(name.getNameByLang(lang)));

		subscriptionTypeRepository.findByCode(subscriberInfDto.getLineLevel().getCode())
				.ifPresent(name -> subscriberInfDto.getLineLevel().setName(name.getNameByLang(lang)));

		logger.info("Stop calling getSubscriberInf. Result: {}", subscriberInfDto.toString());
		
		ServiceResponseDto<SubscriberInfDto> serviceResponse = new ServiceResponseDto<SubscriberInfDto>();
		serviceResponse.setDefaultSuccessResponse();
		serviceResponse.setResultMap(subscriberInfDto);
		
		return serviceResponse;
	}

}
