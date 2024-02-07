package by.com.lifetech.billingapi.utils;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceBusinessError;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.wsdl.BusinessError;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.com.lifetech.billingapi.wsdl.ChainResultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.stream.Collectors;

public class ChainResultToServiceResponseConverter {
    Logger logger = LoggerFactory.getLogger(ChainResultToServiceResponseConverter.class);

    public ServiceResponseDto<Map<String, Object>> getServiceResponse (ChainResult chainResult) {
    	ServiceResponseDto<Map<String, Object>> serviceResponse = new ServiceResponseDto<Map<String, Object>>();
        convert (chainResult, serviceResponse);
        return serviceResponse;
    }

    public ServiceResponseDto<Map<String, Object>> convert (ChainResult chainResult, ServiceResponseDto<Map<String, Object>> serviceResponse) {
        try {
            serviceResponse.setResultCode(chainResult.getResultCode().toString());

            StringBuilder resultDescription = new StringBuilder().append("Chain transactionId: ")
                    .append(chainResult.getTransactionId().toString())
                    .append(chainResult.getResultDescription() != null ? ";" + chainResult.getResultDescription() : "");
            serviceResponse.setResultDescription(resultDescription.toString());

            BusinessError chainBusinessError = chainResult.getBusinessError();
            if (chainBusinessError != null) {
                serviceResponse.setBusinessError(new ServiceBusinessError(chainBusinessError.getErrorName()
                        , chainBusinessError.getErrorMessage()));
            }

            if (chainResult.getResultList() != null && !chainResult.getResultList().isEmpty()) {
                Map<String, Object> resultMap = chainResult.getResultList().stream()
                        .collect(Collectors.toMap(ChainResultElement::getName, ChainResultElement::getValue));
                serviceResponse.setResultMap(resultMap);
            }
        } catch (Exception e) {
            serviceResponse.setDefaultErrorResponse();
            logger.error("Error in parsing chain result to service response: " + e.getMessage());
        }
        return serviceResponse;
    }
}
