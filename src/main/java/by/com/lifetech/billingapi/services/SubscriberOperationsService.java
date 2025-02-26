package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.entity.TransactionHistory;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.PofileIdType;
import by.com.lifetech.billingapi.models.repository.ProductOfferingsRepository;
import by.com.lifetech.billingapi.models.repository.TransactionHistoryRepository;
import by.com.lifetech.billingapi.models.requests.*;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.utils.ProfileResultToServiceResponseConverter;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import by.com.lifetech.billingapi.wsdl.om.ws.result.FulfillResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubscriberOperationsService {
    private final ChainService chainService;
    private final OmProfileService omProfileService;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final ProductOfferingsRepository productOfferingsRepository;
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public ServiceResponseDto<Map<String, Object>> changeTariffLogic(ChangeTariffRequest request)
            throws BusinessException, InternalException {

        String serviceCode = null;
        if (request.getMid() != null && !request.getMid().isEmpty()) {
            serviceCode = productOfferingsRepository.getProductAttribute(request.getMid(), "MID_SERVICE_CODE");
        }
        if (serviceCode != null && !serviceCode.isEmpty()) {
            return specialMidChange(request.getMsisdn(), serviceCode, request.getChannel(), request.getAgent());
        } else {
            return changeTariff(request.getMsisdn(), request.getTariffCode(), request.getChannel(), request.getAgent(), request.getMid());
        }
    }

    public ServiceResponseDto<Map<String, Object>> changeTariff(String msisdn, String tariffName, String channel, String agent, String mid)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        map.put("operation", "START");

        try {
            chainService.executeChain(ChainType.OM, "BreakSession", map);
        } catch (Exception e) {
        }

        map.put("target_tariff_name", tariffName);
        map.put("channel", channel);
        map.put("agent", agent);
        ChainResult chainResult = null;

        try {
            chainResult = chainService.executeChain(ChainType.OM, "change_tarif_best", map);
        } finally {
            map.put("operation", "END");
            try {
                chainService.executeChain(ChainType.OM, "BreakSession", map);
            } catch (Exception e) {
            }
        }

        return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
    }

    public ServiceResponseDto<Map<String, Object>> specialMidChange(String msisdn, String productOfferingCode, String channel, String agent)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        map.put("operation", "START");

        try {
            chainService.executeChain(ChainType.OM, "BreakSession", map);
        } catch (Exception e) {
        }

        map.put("productOfferingCode", productOfferingCode);
        map.put("channel", channel);
        map.put("agent", agent);
        ChainResult chainResult = null;

        try {
            chainResult = chainService.executeChain(ChainType.OM, "special_mid_change", map);
        } finally {
            map.put("operation", "END");
            try {
                chainService.executeChain(ChainType.OM, "BreakSession", map);
            } catch (Exception e) {
            }
        }

        return new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
    }

    public ServiceResponseDto<Map<String, Object>> serviceOperation(ServiceOperationsRequest req) throws InternalException {

        if (req.getOperation().equals(PofileIdType.ORDER_WITHOUT_SEGMENT) && (req.getMapParams().get("Comment")!= null || req.getMapParams().get("SUSPEND_TIME")!= null)){
            req.setOperation(PofileIdType.SUSPEND);
        } else if (req.getOperation().equals(PofileIdType.REFUSE_WITHOUT_SEGMENT) && (req.getMapParams().get("Comment")!= null)){
            req.setOperation(PofileIdType.UNSUSPEND);
        }

        FulfillResult profileResult = omProfileService.fulFillRequest(req.getOperation(), req.getMapParams(),
                req.getCode(), req.getChannel());

        return new ProfileResultToServiceResponseConverter().getServiceResponse(profileResult);
    }

    public ServiceResponseDto<Map<String, Long>> writeTransactionHistory(THistoryEventRequest th) {
        ServiceResponseDto<Map<String, Long>> serviceResponse = new ServiceResponseDto<>();
        serviceResponse.setDefaultSuccessResponse();

        TransactionHistory t = new TransactionHistory();
        t.setAMobileNo(th.getMsisdn());
        t.setAContractCode(th.getContractCode());
        t.setComments(th.getComment());
        t.setTransactionTypeCode(th.getThcode());
        t.setAgentName(th.getAgent());
        t.setEntryDate(LocalDateTime.now());
        t = transactionHistoryRepository.save(t);

        Map<String, Long> map = new HashMap<>();
        map.put("thCode", t.getId());
        serviceResponse.setResultMap(map);

        return serviceResponse;
    }

    public void updateBalance(UpdateBalanceRequest req, String msisdn)
            throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        map.put("balanceCode", req.getBalanceCode());
        map.put("label", req.getPocketCode());
        map.put("comment", req.getComment());
        map.put("amount", req.getAmount());
        map.put("agent", req.getAgent());
        if (req.getStartDate() != null) {
            map.put("dateFrom", Timestamp.valueOf(req.getStartDate()));
        }
        if (req.getEndDate() != null) {
            map.put("dateTo", Timestamp.valueOf(req.getEndDate()));
        }
        chainService.executeChain(ChainType.CIM, "UPDATE_BALANCE", map);
    }

    public ServiceResponseDto<Map<String, Object>> subscriberTermination (SubscriberTerminationRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "crmTerminationLine", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> lossCompensation (SubscriberCompensationRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "loss_compens", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }
}
