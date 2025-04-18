package com.scan_app.output_service.controller;

import com.scan_app.output_service.client.ScanClient;
import com.scan_app.output_service.dto.processCommunication.ScanRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/output/scan")
@RequiredArgsConstructor
@Slf4j
public class StartScanController {

    private final ScanClient scanClient;

    @PostMapping("/send")
    public String sendMessage(@RequestBody ScanRequest scanRequest) {
        scanRequest.setWebSocketId(new ObjectId().toString());
        return scanClient.startScan(scanRequest);
    }
}
