package by.com.lifetech.billingapi.configurations.messaging;

import by.com.lifetech.billingapi.configurations.properties.LogInfoConfig;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.MDC;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AmqpConfiguration {
    @Bean
    public Jackson2JsonMessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue changeTariffRequestQueue() {
        return getDefaultQueue("q_change_tp.request");
    }

    private Queue getDefaultQueue(String name) {
        return new Queue(name, true, false, false, Map.of(
                "x-queue-type", "quorum"
        ));
    }

    @Bean
    public Queue changeTariffResponseQueue() {
        return getDefaultQueue("q_change_tp.response");
    }

    @Bean
    public Queue regNewSimRequestQueue() {
        return getDefaultQueue("q_new_sim.request");
    }

    @Bean
    public Queue regNewSimResponseQueue() {
        return getDefaultQueue("q_new_sim.response");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setAdviceChain(advice());
        return factory;
    }

    @Bean
    public MethodInterceptor advice() {
        return invocation -> {
            try {
                // before
                MDC.put(LogInfoConfig.TRANSACTION_ID_NAME, LogInfoConfig.getCorrelationId());
                MDC.put(LogInfoConfig.CURRENT_USER_NAME, "RabbitMQ");
                // proceed
                invocation.proceed();
                // after
            } finally {
                MDC.remove(LogInfoConfig.TRANSACTION_ID_NAME);
                MDC.remove(LogInfoConfig.CURRENT_USER_NAME);
            }
            return null;
        };
    }
}
