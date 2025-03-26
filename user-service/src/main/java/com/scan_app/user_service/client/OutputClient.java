package com.scan_app.user_service.client;

import com.scan_app.user_service.dto.filter.FilterScansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "scan-service", url = "http://localhost:8081")
public interface OutputClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/output/scans/filterScans")
    public List<String> getFilteredScanIds(@RequestBody FilterScansDto filterScansDto);
}
