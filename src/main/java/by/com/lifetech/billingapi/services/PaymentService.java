package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.ManualPayment;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceBusinessError;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.BusinessErrorCode;
import by.com.lifetech.billingapi.models.enums.ChainType;
import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import by.com.lifetech.billingapi.models.requests.*;
import by.com.lifetech.billingapi.utils.ChainResultToServiceResponseConverter;
import by.com.lifetech.billingapi.wsdl.ChainResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${fm.unrecognized.general}")
    private String agreementUr;
    @Value("${fm.unrecognized.termination}")
    private String agreementUrTerm;
    @Value("${fm.channel-code}")
    private String channelCode;
    private final ChainService chainService;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public ServiceResponseDto<Map<String, Object>> dealerPayment(DealerPaymentRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("MSISDN", req.getMsisdn());
        map.put("PAYMENT_SUM", req.getAmount().toString());
        map.put("PAYER_NAME", req.getPayerName());
        map.put("REPRESENTATIVE_OF_OPERATOR", req.getAgentCode());
        map.put("AGENT", req.getAgent());

        ChainResult chainResult = chainService.executeChain(ChainType.FM, "RefillAvailability", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);

        String operationStatus = chainResult.getResultList().stream().filter(el -> el.getName().equals("Status")).findFirst().orElseThrow().getValue().toString();
        if (!operationStatus.equals("Success")) {
            response.setResultCode(String.valueOf(ServiceResultCode.BUSINESS_ERROR));
            response.setBusinessError(new ServiceBusinessError("Error",
                    chainResult.getResultList().stream().filter(el -> el.getName().equals("Error_desc")).findFirst().orElseThrow().getValue().toString()));
            response.setResultMap(null);
        }
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> manualPayment(ManualPayment req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("paymentDate", req.getPaymentDate().format(formatter));

        String chainName = "";
        if (req instanceof ManualPaymentRequest) {
            chainName = "crmManualPayment";
        } else if (req instanceof ManualPenaltyPaymentRequest){
            chainName = "crmManualPenaltyPayment";
        } else if (req instanceof ManualCorrectionLineRequest) {
            chainName = "manCorrectionForMsisdn";
        } else if (req instanceof ManualCorrectionContractRequest) {
            chainName = "manCorrectionForAgreementNo";
        }
        ChainResult chainResult = chainService.executeChain(ChainType.FM, chainName, map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> cancelPayment(CancelPaymentRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ChainResult chainResult = chainService.executeChain(ChainType.FM, "writeOffForMsisdn", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> reversePayment(CancelPaymentRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        ChainResult chainResult = chainService.executeChain(ChainType.FM, "bankRefundForMsisdn", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> transferToUnrecognized(CancelPaymentRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("channelCode", channelCode);
        map.put("agreementNoUrB", agreementUr);
        ChainResult chainResult = chainService.executeChain(ChainType.FM, "doCorrectionToUnrecognized", map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public ServiceResponseDto<Map<String, Object>> transferToAccount(TransferPaymentRequest req) throws BusinessException, InternalException {
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("channelCode", channelCode);
        String chainName;
        if (req.getMsisdnB() != null && !req.getMsisdnB().isBlank()){
            chainName = "msisdnToMsisdnBTransfer";
        }
        else if (req.getAgreementNoB() !=null && !req.getAgreementNoB().isBlank()){
            chainName = "msisdnToAgreementNoBTransfer";
        }
        else {
            throw new BusinessException(BusinessErrorCode.CONTRACT_NOT_FOUND.name(), "agreementNoB or msisdnB must be present");
        }
        ChainResult chainResult = chainService.executeChain(ChainType.FM, chainName, map);
        ServiceResponseDto<Map<String, Object>> response = new ChainResultToServiceResponseConverter().getServiceResponse(chainResult);
        response.setResultDescription(null);
        return response;
    }

    public void subscriberRefundMoney(SubscriberMoneyRefundRequest req) throws BusinessException, InternalException {
        if (req.getAgreementNoB() == null || req.getAgreementNoB().isBlank()) {
            req.setAgreementNoB(agreementUrTerm);
        }
        Map<String, Object> map = objectMapper.convertValue(req, new TypeReference<>() {});
        map.put("channelCode", channelCode);
        String chainName = switch (req.getRefundReason()) {
            case REFUND_TO_MSISDN -> "subscriberRefundToMsisdn";
            case REFUND_TO_UNRECOGNIZED -> "subscriberRefundToUnrecognized";
            case REFUND_VIA_POST -> "subscriberRefundViaPost";
            case REFUND_TO_CARD -> "";
        };
        chainService.executeChain(ChainType.FM, chainName, map);
    }

    public void chargeTerminationFee(String msisdn) throws BusinessException, InternalException {
        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        chainService.executeChain(ChainType.CIM, "TerminateWithFeeCheck", map);
    }
}
