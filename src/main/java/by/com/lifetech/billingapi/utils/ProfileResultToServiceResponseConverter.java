package by.com.lifetech.billingapi.utils;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceBusinessError;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import by.com.lifetech.billingapi.wsdl.om.ws.result.FulfillResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.GeneralResult.ResultByAppenders.Entry;
import by.com.lifetech.billingapi.wsdl.om.ws.result.ScenarioErrorResult;

public class ProfileResultToServiceResponseConverter {

	Logger logger = LoggerFactory.getLogger(ProfileResultToServiceResponseConverter.class);

    public ServiceResponseDto<Map<String, Object>> getServiceResponse (FulfillResult profileResult) {
    	ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<>();
        convert (profileResult, serviceResponse);
        return serviceResponse;
    }

    public ServiceResponseDto<Map<String, Object>> convert (FulfillResult profileResult, ServiceResponseDto<Map<String, Object>> serviceResponse) {
        try {
        	if (profileResult.getCommonResult().getResultCode().equals("000000")) {
        		serviceResponse.setResultCode(ServiceResultCode.SUCCESS.name());
        	} else if (profileResult.getCommonResult().getResultCode().equals("000010")) {
                serviceResponse.setResultCode(ServiceResultCode.VALIDATION_ERROR.name());
            } else {
        		serviceResponse.setResultCode(ServiceResultCode.INTERNAL_ERROR.name());
        	}

            String resultDescription = "Profile transactionId: " +
                    profileResult.getTransactionId() +
                    (profileResult.getCommonResult().getResultDescription() != null ? ";" + profileResult.getCommonResult().getResultDescription() : "");
            serviceResponse.setResultDescription(resultDescription);

            ScenarioErrorResult scenarioError = profileResult.getScenarioError();
            String resultBusinessCode = profileResult.getCommonResult().getResultBusinessCode();
            if (resultBusinessCode != null) {
                serviceResponse.setResultCode(ServiceResultCode.BUSINESS_ERROR.name());
            	serviceResponse.setBusinessError(new ServiceBusinessError(resultBusinessCode
                        , scenarioError.getDescription()));
            }

            if (profileResult.getResultByAppenders() != null && !profileResult.getResultByAppenders().getEntry().isEmpty()) {
                Map<String, Object> resultMap = profileResult.getResultByAppenders().getEntry().stream()
                        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
                serviceResponse.setResultMap(resultMap);
            }
        } catch (Exception e) {
            serviceResponse.setDefaultErrorResponse();
            logger.error("Error in parsing profile result to service response: {}", e.getMessage());
        }
        return serviceResponse;
    }
}
