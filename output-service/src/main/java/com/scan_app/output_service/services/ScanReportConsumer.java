package com.scan_app.output_service.services;

import com.scan_app.output_service.client.UserClient;
import com.scan_app.output_service.controller.ScanResultWebSocketController;
import com.scan_app.output_service.dto.asynchCommunication.ScanResultMessage;
import com.scan_app.output_service.dto.userServiceCommunication.UserScanSaveRequest;
import com.scan_app.output_service.entity.Nmaprun;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScanReportConsumer {

    private final ScanService scanService;
    private final ScanResultWebSocketController webSocketController;
    private final UserClient userClient;

    @RabbitListener(queues = "reportQueue")
    public void processScanResult(ScanResultMessage message) {
        System.out.println("Obdržena zpráva s ID: " + message);

        // Odeslat výsledek na WebSocket frontend
        try {
            Nmaprun nmaprun = scanService.saveScanJson(message.getJsonData());
            if (message.getUserId() != null) {
                UserScanSaveRequest userScanSaveRequest = new UserScanSaveRequest(nmaprun.get_id(), message.getUserId(), message.getScanType());
                userClient.saveScan(userScanSaveRequest);
            }
            System.out.println("report conusmet " + message.getWebSocketId());
            webSocketController.notifyScanResult(message, nmaprun.get_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
