package com.wsbp.scan_service.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class that changes configuration of RabbitTemplate
 * Converts Java class objects to Json object that goes to message queue
 * Then converts it back when the Json is widthrawed from the queue
 * **/
@Configuration
@RequiredArgsConstructor
public class RabbitTemplateConfig {

    private final ConnectionFactory connectionFactory;
    private final MessageConverter jsonMessageConverter;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
