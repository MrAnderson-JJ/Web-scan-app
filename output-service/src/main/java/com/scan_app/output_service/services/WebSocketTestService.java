package com.scan_app.output_service.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WebSocketTestService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketTestService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 5000) // Každých 5 sekund
    public void sendTestMessage() {
        String testMessage = "test message web socket";
        System.out.println("📡 Posílám testovací zprávu na WS.");
        messagingTemplate.convertAndSend("/topic/scanResults/" + "123", testMessage);
    }
}
