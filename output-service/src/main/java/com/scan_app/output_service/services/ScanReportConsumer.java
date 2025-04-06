package com.scan_app.output_service.services;

import com.scan_app.output_service.controller.ScanResultWebSocketController;
import com.scan_app.output_service.dto.processCommunication.ScanResultMessage;
import com.scan_app.output_service.entity.Nmaprun;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ScanReportConsumer {

    private final ScanService scanService;
    private final ScanResultWebSocketController webSocketController;
    private final UserScanService userScanService;

    @RabbitListener(queues = "reportQueue")
    public void processScanResult(ScanResultMessage message) {

        // Send result to WebSocket frontend
        if (message.getJsonData() == null) {
            webSocketController.notifyScanResult(message, "null");
            return;
        }
        try {
            Nmaprun nmaprun = scanService.saveScanJson(message.getJsonData());
            userScanService.saveScan(message.getUserId(), message.getScanIp(), nmaprun, message.getScanType());
            webSocketController.notifyScanResult(message, nmaprun.get_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
