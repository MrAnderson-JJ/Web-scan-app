package com.scan_app.output_service.client;

import com.scan_app.output_service.dto.RabbitDto.ScanRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "scan-service", url = "http://localhost:8080")
public interface ScanClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/scan/send")
    public String startScan(@RequestBody ScanRequest scanRequest);
}
