package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.cboss.GetInformationSubscriberDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.repository.ProductOfferingsRepository;
import by.com.lifetech.billingapi.models.requests.SaluteServiceRequest;
import by.com.lifetech.billingapi.models.responses.SaluteCheckActiveResponse;
import by.com.lifetech.billingapi.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SaluteService {
	
	Logger logger = LoggerFactory.getLogger(SaluteService.class);
	private final CBossService cBossService;
	private final ExceptionUtils exceptionUtils;
    private final ProductOfferingsRepository productOfferingsRepository;
    private final ChainService chainService;
    private final String PROFILE_ID_ATTRIBUTE = "SALUTE_PROFILE_ID";
    private final String SALUTE_CHANNEL = "SALUTE";
	
	public SaluteCheckActiveResponse isSubscriptionActive(String msisdn) throws BusinessException, InternalException {
		
		GetInformationSubscriberDto subs = cBossService.getInformation(msisdn).getSubscriber();
        if (subs == null) {
            throw exceptionUtils.getMsisdnNotFoundException();
        }

        Set<String> blockedTariffsSet = Set.of("INFPROMAX", "BKASSA");
        SaluteCheckActiveResponse response = new SaluteCheckActiveResponse(1, false);
        
        if (blockedTariffsSet.contains(subs.getTariffPlan())) {
        	response.setStatus(0);
        }
		return response;
	}

    public ServiceResponseDto<Map<String, Object>> operateSaluteService(SaluteServiceRequest saluteServiceRequest) throws BusinessException, InternalException {
        String serviceCode = productOfferingsRepository
                .getProductCodeByAttributeValue(PROFILE_ID_ATTRIBUTE, saluteServiceRequest.getProfileId()).orElse("");
        if (serviceCode.isEmpty()) {
            throw new BusinessException("PROFILE_ID_NOT_FOUND", String.format("Service for ProfileId = %s not found", saluteServiceRequest.getProfileId()));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", saluteServiceRequest.getMsisdn());
        map.put("productOfferingCode", serviceCode);
        map.put("channel", SALUTE_CHANNEL);
        chainService.executeChain(ChainType.OM, saluteServiceRequest.getOperation().getChainName(), map);

        return new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();
    }
}
