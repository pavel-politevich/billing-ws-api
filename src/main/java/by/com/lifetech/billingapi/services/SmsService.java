package by.com.lifetech.billingapi.services;

import by.com.lifetech.billingapi.exceptions.ExternalServiceException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.requests.SmsBasicRequest;
import by.com.lifetech.billingapi.wsdl.message.GeneralResult;
import by.com.lifetech.billingapi.wsdl.message.GetAllMessagesResult;
import by.com.lifetech.billingapi.wsdl.message.MessageDescriptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SmsService {
    Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final SmsMessageService smsMessageService;

    public ServiceResponseDto<Map<String, Object>> sendSmsWithParameter(SmsBasicRequest request) throws ExternalServiceException {
        GeneralResult smsResult = smsMessageService.sendSmsWithMapParams(request.getSmsCode(), request.getMsisdn(), request.getParams());
        return getServiceResponseBySmsResponse(smsResult);
    }

    public ServiceResponseDto<Map<String, Object>> getAllSms() throws ExternalServiceException {
        GetAllMessagesResult smsResult = smsMessageService.getAllMessages();
        return getServiceResponseBySmsResponse(smsResult);
    }

    public ServiceResponseDto<MessageDescriptor> getSmsByCode(String smsCode) throws ExternalServiceException {
        ServiceResponseDto<MessageDescriptor> response = new ServiceResponseDto<MessageDescriptor>().setDefaultSuccessResponse();

        GetAllMessagesResult smsResult = smsMessageService.getAllMessages();
        MessageDescriptor messageDescriptor = smsResult.getMessages().stream()
                .filter(m -> m.getMessageCode().equals(smsCode))
                .findFirst().orElse(null);

        response.setResultMap(messageDescriptor);
        return response;
    }

    private ServiceResponseDto<Map<String, Object>> getServiceResponseBySmsResponse(GeneralResult result) {
        ServiceResponseDto<Map<String, Object>> response = new ServiceResponseDto<Map<String, Object>>().setDefaultSuccessResponse();

        String description = String.format("Sms transactionId: %s, description: %s"
                , Optional.ofNullable(result.getTransactionId()).orElse("")
                , Optional.ofNullable(result.getResultDescription()).orElse(""));
        response.setResultDescription(description);

        if (result instanceof GetAllMessagesResult) {
            response.setResultMap(Map.of("messages", ((GetAllMessagesResult) result).getMessages()));
        }

        return response;
    }
}
