package com.wsbp.scan_service.controller;

import com.wsbp.scan_service.dto.ScanRequest;
import com.wsbp.scan_service.service.ScanProducer;
import com.wsbp.scan_service.service.StartScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scan")
@RequiredArgsConstructor
@Slf4j
public class ScanController {

    private final StartScanService startScanService;
    private final ScanProducer scanProducer;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void startScan(@RequestBody ScanRequest scanRequest) {
        startScanService.startScanByUser(scanRequest);
        log.info("Scan started in controller");
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody ScanRequest scanRequest) {
        System.out.println("websocketid here 2> " + scanRequest.getWebSocketId());
        scanProducer.sendMessage(scanRequest);
        System.out.println("websocketid here 3> " + scanRequest.getWebSocketId());
        return scanRequest.getWebSocketId();
    }

    @PostMapping("/sendtypes")
    public String sendMessageToScan(@RequestBody ScanRequest scanRequest) {
        scanProducer.sendMessage(scanRequest);
        return scanRequest.getWebSocketId();
    }
}
