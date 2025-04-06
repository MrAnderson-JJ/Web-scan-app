package com.scan_app.output_service.controller;

import com.scan_app.output_service.dto.FilterScansDto;
import com.scan_app.output_service.dto.scan.HostDto;
import com.scan_app.output_service.dto.scan.NmapRunDto;
import com.scan_app.output_service.dto.scan.PingDto;
import com.scan_app.output_service.entity.Nmaprun;
import com.scan_app.output_service.services.ScanService;
import com.scan_app.output_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/output/scans")
public class GetScanController {

    @Autowired
    private ScanService scanService;
    @Autowired
    private UserService userService;

    @GetMapping("/getintense/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HostDto> getIntenseScanByScanId(@PathVariable String id) {
        return scanService.getQuickScanByScanId(id);
    }

    @GetMapping("/getLatestUserScan")
    @ResponseStatus(HttpStatus.OK)
    public NmapRunDto getLatestUserScan(@RequestHeader("X-User-ID") String userId) {
        return userService.getLatestUserScan(userId);
    }

    @PostMapping("/filterScans")
    @ResponseStatus(HttpStatus.OK)
    public List<String> filterScans(@RequestBody FilterScansDto filterScansDto) {
        try {
            return scanService.filterScans(filterScansDto);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
