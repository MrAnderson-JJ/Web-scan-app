package com.wsbp.scan_service.service;

import com.wsbp.scan_service.configuration.RabbitMqConfig;
import com.wsbp.scan_service.dto.ScanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScanConsumer {

    private final StartScanService startScanService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage(ScanRequest scanRequest) {
        startScanService.startScanByUser(scanRequest);
    }
}
