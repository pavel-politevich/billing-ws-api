package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.ChainException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.CreditServicesAvailabilityDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.requests.*;
import by.com.lifetech.billingapi.models.responses.ActivationCheckResultResponse;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ActivationChecksService {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Logger logger = LoggerFactory.getLogger(ActivationChecksService.class);
    private final ChainService chainService;
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public ServiceResponseDto<ActivationCheckResultResponse> checkBlacklist(CheckByPassportRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkBlacklist");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkFraud(CheckByPassportRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkFraud");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkMIA(CheckMvdNtecRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("aliveDuring", req.getAliveDuring().format(DATE_TIME_FORMATTER));
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkMIA");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkNTEC(CheckMvdNtecRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("aliveDuring", req.getAliveDuring().format(DATE_TIME_FORMATTER));
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkNTEC");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkLimitationSimCard(CheckByPassportRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkLimitSim");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkDebt(CheckByPassportRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkDebt");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkByCategory(CheckMidRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("aliveDuring", req.getAliveDuring().format(DATE_TIME_FORMATTER));
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkOfferGroup");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkTerminalGroup(CheckTerminalRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkTerminalGroup");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkObligationByMsisdn(String msisdn)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkObligationsByMsisdn");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkCreditsByMsisdn(String msisdn)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkCreditServicesMsisdn");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkTerrListInd(CheckByFioRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("aliveDuring", req.getAliveDuring().format(DATE_TIME_FORMATTER));
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkTerrListInd");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkByPassport(CheckByPassportRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkByPassportShort");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<ActivationCheckResultResponse> checkByPassportAndFio(CheckMvdNtecRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("aliveDuring", req.getAliveDuring().format(DATE_TIME_FORMATTER));
        ActivationCheckResultResponse checkResult = getCheckResult(map, "checkByFIOandPassport");
        ServiceResponseDto<ActivationCheckResultResponse> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<CreditServicesAvailabilityDto> callCreditChecks(String msisdn)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        ChainResult chainResult;
        try {
            chainResult = chainService.executeChain(ChainType.OM, "credit_checks", map);
        } catch (ChainException e) {
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }
        ServiceResponseDto<CreditServicesAvailabilityDto> serviceResponse = new ServiceResponseDto<>();
        CreditServicesAvailabilityDto checkResult;
        try {
            checkResult = objectMapper
                    .readValue(
                            chainResult.getResultList().stream().filter(el -> el.getName().equals("result"))
                                    .findFirst().orElseThrow().getValue().toString(),
                            new TypeReference<>() {
                            });
        } catch (JsonProcessingException e) {
            logger.error("Can not parse chain response. Error: {}", e.getMessage());
            throw new BusinessException(BusinessErrorCode.SUBSCRIBER_NOT_FOUND.name(), BusinessErrorCode.SUBSCRIBER_NOT_FOUND.getMessage());
        }
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(checkResult);
        return serviceResponse;
    }

    public ServiceResponseDto<Map<String, Object>> checkSecretTariff(CheckSecretTariffRequest req)
            throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("birthday", java.sql.Date.valueOf(req.getBirthday()));
        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "check_secret_tariff_availability", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    private ActivationCheckResultResponse getCheckResult(Map<String, Object> map, String chainName) throws BusinessException, InternalException {
        ActivationCheckResultResponse checkResult = new ActivationCheckResultResponse();
        ChainResult chainResult;
        chainResult = chainService.executeChain(ChainType.CIM, chainName, map);
        List<String> errorList;
        try {
            errorList = objectMapper
                    .readValue(chainResult.getResultList().stream().filter(el -> el.getName().equals("ERROR_LIST"))
                            .findFirst().orElseThrow().getValue().toString(), new TypeReference<>() {}
                    );
        } catch (JsonProcessingException e) {
            throw new InternalException("Chain response error");
        }
        checkResult.getErrorList().addAll(errorList);
        if (checkResult.getErrorList().isEmpty()) {
            checkResult.setPassed(true);
        }
        return checkResult;
    }
}
