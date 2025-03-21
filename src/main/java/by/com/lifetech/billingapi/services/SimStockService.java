package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.repository.NumberSelectionCategoryRepository;
import by.com.lifetech.billingapi.models.requests.MsisdnChangeRequest;
import by.com.lifetech.billingapi.models.requests.SimChangeRequest;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@RequiredArgsConstructor
public class SimStockService {
    private final ChainService chainService;
    private final NumberSelectionCategoryRepository selectionCategoryRepository;
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).configure(MapperFeature.USE_ANNOTATIONS, false).build();
    Logger logger = LoggerFactory.getLogger(SimStockService.class);

    public ServiceResponseDto<Map<String, Object>> changeSim (SimChangeRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "change_equipment", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> changeMsisdn (MsisdnChangeRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ChainResult chainResult = chainService.executeChain(ChainType.CIM, "msisdn_change_mod", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    private void confirmReservationMsisdn(String msisdn) throws BusinessException {
        int result = Integer.parseInt(selectionCategoryRepository.confirmReserveMsisdn(msisdn));
        if (result < 0) {
            logger.error("Error calling sim_stock.csp_api.confirmreservmsisdn with code = {}", result);
            throw new BusinessException("ERR40059", "SimStock API error");
        }
    }

    private void lockReservationMsisdn(String msisdn) throws BusinessException {
        int result = Integer.parseInt(selectionCategoryRepository.lockReservedMsisdn(msisdn));
        if (result < 0) {
            logger.error("Error calling sim_stock.csp_api.lockReservedMsisdn with code = {}", result);
            throw new BusinessException("ERR40059", "SimStock API error");
        }
    }

    private void cancelReservationMsisdn(String msisdn) throws BusinessException {
        int result = Integer.parseInt(selectionCategoryRepository.cancelReserveMsisdn(msisdn));
        if (result < 0) {
            logger.error("Error calling sim_stock.csp_api.cancelReservMsisdn with code = {}", result);
            throw new BusinessException("ERR40059", "SimStock API error");
        }
    }

    public String lockReservationMsisdnByType(String reservationType) throws BusinessException {
        String result = selectionCategoryRepository.lockReserveRandomMsisdn(reservationType);
        int cnt = 3;
        String msisdn = null;
        while (cnt > 0 && msisdn == null) {
            msisdn = selectionCategoryRepository.lockReserveRandomMsisdn(reservationType);
            cnt--;
        }
        if (result == null) {
            logger.error("No available MSISDN's in sim-stock by reservationType= {}!", reservationType);
            throw new BusinessException("ERR40059", "SimStock API error");
        }
        return msisdn;
    }
}
