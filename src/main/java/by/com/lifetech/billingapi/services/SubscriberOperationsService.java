package by.com.lifetech.billingapi.services;

import java.util.HashMap;
import java.util.Map;

import by.com.lifetech.billingapi.models.dto.ServiceOperationsRequestDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.utils.ProfileResultToServiceResponseConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.FulfillResult;

@Service
public class SubscriberOperationsService {

	@Autowired
	ChainService chainService;
	
	@Autowired
	OmProfileService omProfileService;

	Logger logger = LoggerFactory.getLogger(SubscriberOperationsService.class);

	@Value("${app.default.locale}")
	private String defaultLocale;

	@Value("${app.default.channel}")
	private String defaultChannel;

	

	public ServiceResponseDto<Map<String, Object>> changeTariff(String msisdn, String tariffName, String channel, String agent)
			throws BusinessException {

		logger.info("START changeTariff with params: msisdn= {}, tariffName= {}, channel= {}, agent= {}", msisdn,
				tariffName, channel, agent);

		Map<String, Object> map = new HashMap<>();
		map.put("msisdn", msisdn);
		map.put("operation", "START");

		chainService.executeChain(ChainType.OM, "BreakSession", map);

		map.put("target_tariff_name", tariffName);
		map.put("channel", channel);
		map.put("agent", agent);
		ChainResult chainResult = null;

		try {
			chainResult = chainService.executeChain(ChainType.OM, "change_tarif_best", map);
		} catch (Exception e) {
			throw new BusinessException("change tariff error");

		} finally {
			map.put("operation", "END");
			chainService.executeChain(ChainType.OM, "BreakSession", map);
		}

		return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
	}

	public ServiceResponseDto<Map<String, Object>> specialMidChange(String msisdn, String productOfferingCode, String channel, String agent)
			throws BusinessException {

		logger.info("START specialMidChange with params: msisdn= {}, tariffName= {}, channel= {}, agent= {}", msisdn,
				productOfferingCode, channel, agent);

		Map<String, Object> map = new HashMap<>();
		map.put("msisdn", msisdn);
		map.put("operation", "START");

		chainService.executeChain(ChainType.OM, "BreakSession", map);

		map.put("productOfferingCode", productOfferingCode);
		map.put("channel", channel);
		map.put("agent", agent);
		ChainResult chainResult = null;

		try {
			chainResult = chainService.executeChain(ChainType.OM, "special_mid_change", map);
		} catch (Exception e) {
			throw new BusinessException("change tariff error");

		} finally {
			map.put("operation", "END");
			chainService.executeChain(ChainType.OM, "BreakSession", map);
		}

		return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
	}
	
	public ServiceResponseDto<Map<String, Object>> serviceOperation(ServiceOperationsRequestDto req) throws BusinessException {

		FulfillResult profileResult = omProfileService.fulFillRequest(req.getOperation(), req.getMapParams(),
				req.getCode(), req.getChannel());

		return new ProfileResultToServiceResponseConverter().getServiceResponse(profileResult);
	}

}
