package com.scan_app.output_service.controller;

import com.scan_app.output_service.dto.processCommunication.ScanResultMessage;
import com.scan_app.output_service.dto.processCommunication.ScanToFrontendMessage;
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
        messagingTemplate.convertAndSend("/topic/scanResults/" + message.getWebSocketId(), scanToFrontendMessage);
    }
}
