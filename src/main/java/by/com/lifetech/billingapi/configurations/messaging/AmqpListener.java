package by.com.lifetech.billingapi.configurations.messaging;

import by.com.lifetech.billingapi.exceptions.BusinessException;
import by.com.lifetech.billingapi.exceptions.InternalException;
import by.com.lifetech.billingapi.models.dto.service_response.ServiceResponseDto;
import by.com.lifetech.billingapi.models.enums.ServiceResultCode;
import by.com.lifetech.billingapi.models.requests.ChangeTariffRequest;
import by.com.lifetech.billingapi.models.requests.RegistrationSimRequest;
import by.com.lifetech.billingapi.services.AmqpService;
import by.com.lifetech.billingapi.services.SubscriberActivationService;
import by.com.lifetech.billingapi.services.SubscriberOperationsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AmqpListener {
    public static final String Q_CHANGE_TP_RESPONSE = "q_change_tp.response";
    public static final String Q_CHANGE_TP_REQUEST = "q_change_tp.request";
    public static final String Q_NEW_SIM_RESPONSE = "q_new_sim.response";
    public static final String Q_NEW_SIM_REQUEST = "q_new_sim.request";
    private final SubscriberOperationsService operationsService;
    private final SubscriberActivationService subscriberActivationService;
    private final AmqpService amqpService;
    Logger logger = LoggerFactory.getLogger(AmqpListener.class);

    @RabbitListener(queues = Q_CHANGE_TP_REQUEST, messageConverter = "jsonConverter")
    public void processChangeTariffMessage(ChangeTariffRequest in) {
        logger.info("Message {} read from Queue {}", in, Q_CHANGE_TP_REQUEST);
        ServiceResponseDto<Map<String, Object>> response = new ServiceResponseDto<>();
        try {
            response = operationsService.changeTariffLogic(in);
        } catch (BusinessException e) {
            response.setResultCode(ServiceResultCode.BUSINESS_ERROR.name());
            response.setBusinessError(e.getError());
        } catch (InternalException e) {
            response.setResultCode(ServiceResultCode.INTERNAL_ERROR.name());
        } finally {
            amqpService.sendMessage(response, Q_CHANGE_TP_RESPONSE);
        }
    }

    @RabbitListener(queues = Q_NEW_SIM_REQUEST, messageConverter = "jsonConverter")
    public void processEmptyRegistrationMessage(RegistrationSimRequest in) {
        logger.info("Message {} read from Queue {}", in, Q_NEW_SIM_REQUEST);
        ServiceResponseDto<Map<String, Object>> response = new ServiceResponseDto<>();
        try {
            subscriberActivationService.crmEmptySimRegistration(in);
        } catch (BusinessException e) {
            response.setResultCode(ServiceResultCode.BUSINESS_ERROR.name());
            response.setBusinessError(e.getError());
        } catch (InternalException e) {
            response.setResultCode(ServiceResultCode.INTERNAL_ERROR.name());
        } finally {
            amqpService.sendMessage(response, Q_NEW_SIM_RESPONSE);
        }
    }
}
