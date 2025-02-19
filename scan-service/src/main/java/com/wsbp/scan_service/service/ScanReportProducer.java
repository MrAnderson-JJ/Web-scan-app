package com.wsbp.scan_service.service;

import com.wsbp.scan_service.configuration.RabbitMqConfig;
import com.wsbp.scan_service.dto.ScanRequest;
import com.wsbp.scan_service.dto.ScanResultMessage;
import com.wsbp.scan_service.scanUtil.ScanTypes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanReportProducer {

    private RabbitTemplate rabbitTemplate;

    public ScanReportProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String webSocketId ,String jsonOutput, ScanTypes scanType) {
        ScanResultMessage message = new ScanResultMessage(webSocketId, jsonOutput);
        System.out.println(message.getWebSocketId());
        System.out.println(message.getJsonData());
        ScanRequest scanRequest = new ScanRequest();
        scanRequest.setIp("scanme");
        scanRequest.setOptions(List.of("-sn"));
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_REPORT, RabbitMqConfig.ROUTING_REPORT, message);
        System.out.println("Zpráva odeslána: " + webSocketId);
    }
}
