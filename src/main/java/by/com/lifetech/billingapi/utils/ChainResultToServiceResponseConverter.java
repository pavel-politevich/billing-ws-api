package by.com.lifetech.billingapi.utils;

import by.com.lifetech.billingapi.models.dto.service_response.ServiceBusinessError;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.wsdl.BusinessError;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.com.lifetech.billingapi.wsdl.ChainResultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
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

    public static Map<String, Object> convertResultListToMap(ChainResult chainResult) {
        if (chainResult == null || chainResult.getResultList() == null || chainResult.getResultList().isEmpty()) {
            return new HashMap<>();
        }
        return chainResult.getResultList().stream()
                .collect(Collectors.toMap(ChainResultElement::getName, ChainResultElement::getValue));
    }

    public static Object getObjectFromResultList(ChainResult chainResult, String fieldName) throws NoSuchElementException {
        if (chainResult == null || chainResult.getResultList() == null || chainResult.getResultList().isEmpty()) {
            throw new NoSuchElementException();
        }
        return chainResult.getResultList().stream().filter(el -> el.getName().equals(fieldName))
                .map(ChainResultElement::getValue).findFirst().orElseThrow();
    }

    public static Map<String, Object> objectToMap(Object object) throws IllegalAccessException {
        return objectToMap(object, true);
    }

    public static Map<String, Object> objectToMap(Object object, boolean isSetNullValues) throws IllegalAccessException {
        Map<String, Object> fieldMap = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(object);
            if (value != null || isSetNullValues) {
                fieldMap.put(field.getName(), value);
            }
        }
        return fieldMap;
    }
}
