package by.com.lifetech.billingapi.services;

import java.time.LocalDateTime;
import java.util.Optional;
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
import by.com.lifetech.billingapi.models.dto.TariffDto;
import by.com.lifetech.billingapi.models.entity.RegistrationHistory;
import by.com.lifetech.billingapi.models.entity.dictionary.ChannelLocalizations;
import by.com.lifetech.billingapi.models.enums.Lang;
import by.com.lifetech.billingapi.models.repository.RegistrationHistoryRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.ChannelLocalizationsRepository;
import by.com.lifetech.billingapi.models.repository.dictionary.RegistrationTypeRepository;

@Service
public class SubscriberInfoService {

	@Autowired
	private RegistrationHistoryRepository registrationHistoryRepository;

	@Autowired
	private RegistrationTypeRepository registrationTypeRepository;

	@Autowired
	private ChannelLocalizationsRepository channelLocalizationsRepository;

	Logger logger = LoggerFactory.getLogger(SubscriberInfoService.class);

	@Value("${app.default.locale}")
	private String defaultLocale;

	@Value("${app.default.channel}")
	private String defaultChannel;

	public Page<RegistrationHistory> getRegistrationHistory(RegHistoryRequestDto req, Pageable page)
			throws BusinessException {

		logger.info("Start calling getRegistrationHistory with: {}", req.toString());

		LocalDateTime startDt;
		LocalDateTime endDt;
		Lang lang;
		Page<RegistrationHistory> regHistories;

		if (req.getDateFrom() != null) {
			startDt = req.getDateFrom();
		} else
			startDt = LocalDateTime.now().minusDays(1L);

		if (req.getDateTo() != null) {
			endDt = req.getDateTo();
		} else
			endDt = LocalDateTime.now();

		try {
			lang = Lang.getByValue(req.getLanguage());

			regHistories = registrationHistoryRepository.findAllByFields(startDt, endDt, req.getSalesPoint(),
					req.getTariff().getCode(), req.getAgent(), req.getCategory().getCode(),
					req.getRegistrationType().getCode(), req.getContractCode(), page);
		} catch (IllegalArgumentException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("Error while retrieving data from database");
		}

		for (RegistrationHistory rh : regHistories) {

			TariffDto tariff = new TariffDto(rh.getTariff(), getChannelLocalName(rh.getTariff(), lang));
			CategoryDto category = new CategoryDto(rh.getCategory(),
					getChannelLocalName(rh.getCategory(), lang));
			RegistrationTypeDto regType = new RegistrationTypeDto(rh.getRegistrationType(),
					getRegistrationTypeName(rh.getRegistrationType(), lang));

			rh.setTariffDto(tariff);
			rh.setCategoryDto(category);
			rh.setRegistrationTypeDto(regType);
		}

		return regHistories;
	}

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

	public String getRegistrationTypeName(String registrationTypeCode, Lang lang) {
		logger.debug("Registration type name for code {} is {}", registrationTypeCode,
				registrationTypeRepository.findByRegistrationTypeCode(registrationTypeCode));
		switch (lang) {
		case RU:
			return registrationTypeRepository.findByRegistrationTypeCode(registrationTypeCode).getNameRu();
		case EN:
			return registrationTypeRepository.findByRegistrationTypeCode(registrationTypeCode).getNameEn();
		default:
			return registrationTypeRepository.findByRegistrationTypeCode(registrationTypeCode).getNameRu();
		}

	}

}
