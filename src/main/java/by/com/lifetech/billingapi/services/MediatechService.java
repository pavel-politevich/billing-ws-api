package by.com.lifetech.billingapi.services;

import java.util.HashMap;
import java.util.Map;

import by.com.lifetech.billingapi.exceptions.InternalException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.requests.TVplusPurchaseRequest;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.wsdl.ChainResult;

@Service
@RequiredArgsConstructor
public class MediatechService {
	private final ChainService chainService;

    Logger logger = LoggerFactory.getLogger(MediatechService.class);

	public ServiceResponseDto<Map<String, Object>> tvPlusGetInfo(String msisdn)
			throws BusinessException, InternalException {
		Map<String, Object> map = new HashMap<>();
		map.put("msisdn", msisdn);
		ChainResult chainResult = chainService.executeChain(ChainType.OM, "TVplus_getInfo", map);

		return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
	}
	
	public ServiceResponseDto<Map<String, Object>> tvPlusPurchase(TVplusPurchaseRequest req)
			throws BusinessException, InternalException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = objectMapper
			      .convertValue(req, new TypeReference<Map<String, Object>>() {});
		
		ChainResult chainResult = chainService.executeChain(ChainType.OM, "TVplus_OTP", map);

		return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
	}
}
