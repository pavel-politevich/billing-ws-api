package by.com.lifetech.billingapi.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmqpService {
    private final AmqpTemplate amqpTemplate;
    Logger logger = LoggerFactory.getLogger(AmqpService.class);

    public void sendMessage(Object object, String queueName) {
        amqpTemplate.convertAndSend(queueName, object);
        logger.info("Message {} send to Queue {}", object, queueName);
    }
}
