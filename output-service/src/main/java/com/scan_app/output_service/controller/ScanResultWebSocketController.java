package com.scan_app.output_service.controller;

import com.scan_app.output_service.dto.asynchCommunication.ScanResultMessage;
import com.scan_app.output_service.dto.asynchCommunication.ScanToFrontendMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ScanResultWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public ScanResultWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Method to send WebSocket messages from backend to frontend
    public void notifyScanResult(ScanResultMessage message, String scanId) {
        ScanToFrontendMessage scanToFrontendMessage = new ScanToFrontendMessage(scanId, message);
        System.out.println("Sending WebSocket message: " + message.getWebSocketId());
        System.out.println("Sending WebSocket message: " + message.getScanType());
        messagingTemplate.convertAndSend("/topic/scanResults/" + message.getWebSocketId(), scanToFrontendMessage);
    }
}
