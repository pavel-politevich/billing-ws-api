package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.OnlineRegistrationDto;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.RegistrationServicesByTp;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.HubActivationResult;
import by.com.lifetech.billingapi.models.enums.MVDResult;
import by.com.lifetech.billingapi.models.repository.RegistrationServicesByTpRepository;
import by.com.lifetech.billingapi.models.requests.RegServicesRequest;
import by.com.lifetech.billingapi.models.requests.RegistrationExistingRequest;
import by.com.lifetech.billingapi.models.requests.RegistrationSimRequest;
import by.com.lifetech.billingapi.models.requests.RegistrationWithoutOblRequest;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.utils.EnumUtils;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SubscriberActivationService {
    Logger logger = LoggerFactory.getLogger(SubscriberActivationService.class);

    private final ChainService chainService;
    private final SimStockService simStockService;
    private final RegistrationServicesByTpRepository servicesByTpRepository;

    public ServiceResponseDto<OnlineRegistrationDto> onlineRegistrationLifeId(RegistrationSimRequest request) throws InternalException {
        Map<String, Object> requestMap;
        try {
            requestMap = ChainResultToServiceResponseConverter.objectToMap(request, false);
        } catch (Exception e) {
            throw new InternalException("Request parsing error");
        }

        OnlineRegistrationDto onlineRegistrationDto = new OnlineRegistrationDto();
        onlineRegistrationDto.setStartValues();
        try {
            ChainResult chainResult = chainService.executeChain(ChainType.CIM, "onlineRegistration_crm", requestMap);
            setOnlineRegistrationDtoFromChainResult(chainResult, onlineRegistrationDto);
        } catch (BusinessException e) {
            onlineRegistrationDto.setCheckReason(e.getError().getErrorName());
        } catch (Exception e) {
            logger.error("Error for subscriber online registration with message: {}", e.getMessage());
        }

        ServiceResponseDto serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setResultMap(onlineRegistrationDto);
        return serviceResponse.setDefaultSuccessResponse();
    }

    private void setOnlineRegistrationDtoFromChainResult(ChainResult chainResult, OnlineRegistrationDto onlineRegistrationDto) {
        HubActivationResult activationResult = EnumUtils.getEnumValueByName(HubActivationResult.class
                ,ChainResultToServiceResponseConverter.getObjectFromResultList(chainResult, "activationResult")
                        .toString().toUpperCase());
        MVDResult mvdResult = EnumUtils.getEnumValueByName(MVDResult.class
                ,ChainResultToServiceResponseConverter.getObjectFromResultList(chainResult, "checkMIA")
                        .toString().toUpperCase());

        onlineRegistrationDto.setActivationResult(activationResult);
        onlineRegistrationDto.setCheckMIA(mvdResult);
        onlineRegistrationDto.setMsisdn(ChainResultToServiceResponseConverter.getObjectFromResultList(chainResult, "msisdn").toString());
        onlineRegistrationDto.setCheckReason(ChainResultToServiceResponseConverter.getObjectFromResultList(chainResult, "checkReason").toString());
        onlineRegistrationDto.setContractId(ChainResultToServiceResponseConverter.getObjectFromResultList(chainResult, "contractId").toString());
    }

    public ServiceResponseDto<Map<String, Object>> onlineChecksLifeId(RegistrationSimRequest request) throws InternalException, BusinessException {
        Map<String, Object> requestMap;
        try {
            requestMap = ChainResultToServiceResponseConverter.objectToMap(request, false);
        } catch (Exception e) {
            throw new InternalException("Request parsing error");
        }

        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "onlineChecks", requestMap);
        return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
    }

    public ServiceResponseDto<Map<String, Object>> crmEmptySimRegistration(RegistrationSimRequest request) throws InternalException, BusinessException {
        Map<String, Object> requestMap;
        if (request.getMsisdn() == null || request.getMsisdn().isBlank()) {
            String msisdn = simStockService.lockReservationMsisdnByType("16000");
            request.setMsisdn(msisdn);
        }
        try {
            requestMap = ChainResultToServiceResponseConverter.objectToMap(request, false);
        } catch (Exception e) {
            throw new InternalException("Request parsing error");
        }
        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "individual_registration_new_sim", requestMap);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> crmExistingRegistration(RegistrationExistingRequest request) throws InternalException, BusinessException {
        Map<String, Object> requestMap;
        try {
            requestMap = ChainResultToServiceResponseConverter.objectToMap(request, false);
        } catch (Exception e) {
            throw new InternalException("Request parsing error");
        }
        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "existing_registration_to_the_offer", requestMap);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> crmWithoutObligationRegistration(RegistrationWithoutOblRequest request) throws InternalException, BusinessException {
        Map<String, Object> requestMap;
        try {
            requestMap = ChainResultToServiceResponseConverter.objectToMap(request, false);
        } catch (Exception e) {
            throw new InternalException("Request parsing error");
        }
        requestMap.put("productOfferingCode","S_DEVICE_WITHOUT_OFFER");
        ChainResult chainResult = chainService.executeChain(ChainType.OM, "device_act_deact", requestMap);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    @Transactional
    public ServiceResponseDto<List<RegistrationServicesByTp>> getServicesForActivation(RegServicesRequest request) {
        List<RegistrationServicesByTp> resList;
            resList = servicesByTpRepository.findIUIServices(
                    request.getTariffCode(),
                    request.getTid(),
                    request.getMid(),
                    request.getPointOfSaleCode()
            );
        ServiceResponseDto<List<RegistrationServicesByTp>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();
        serviceResponse.setResultMap(resList);
        return serviceResponse;
    }

}
