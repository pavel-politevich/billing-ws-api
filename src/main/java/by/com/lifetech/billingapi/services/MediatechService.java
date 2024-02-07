package by.com.lifetech.billingapi.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.TVplusPurchaseRequestDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.wsdl.ChainResult;

@Service
public class MediatechService {
	
	@Autowired
	ChainService chainService;
	
	@Autowired
	OmProfileService omProfileService;

	Logger logger = LoggerFactory.getLogger(MediatechService.class);
	
	public ServiceResponseDto<Map<String, Object>> TVPlusGetInfo(String msisdn)
			throws BusinessException {

		logger.info("START TVplus getInfo with params: msisdn= {}", msisdn);

		Map<String, Object> map = new HashMap<>();
		map.put("msisdn", msisdn);
		ChainResult chainResult = chainService.executeChain(ChainType.OM, "TVplus_getInfo", map);

		return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
	}
	
	public ServiceResponseDto<Map<String, Object>> TVPlusPurchase(TVplusPurchaseRequestDto req)
			throws BusinessException {

		logger.info("START TVplus OTP with params: {}", req);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = objectMapper
			      .convertValue(req, new TypeReference<Map<String, Object>>() {});
		
		ChainResult chainResult = chainService.executeChain(ChainType.OM, "TVplus_OTP", map);

		return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
	}

}
