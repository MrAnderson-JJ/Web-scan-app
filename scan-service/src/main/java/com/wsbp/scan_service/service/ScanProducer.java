package com.wsbp.scan_service.service;

import com.wsbp.scan_service.configuration.RabbitMqConfig;
import com.wsbp.scan_service.dto.ScanRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScanProducer {

    private RabbitTemplate rabbitTemplate;

    public ScanProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ScanRequest scanRequest) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, scanRequest);
        System.out.println("Zpráva odeslána: " + scanRequest);
    }
}
