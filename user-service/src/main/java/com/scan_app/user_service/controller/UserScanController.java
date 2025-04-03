package com.scan_app.user_service.controller;

import com.scan_app.user_service.dto.CheckScanRequest;
import com.scan_app.user_service.dto.UserScanResponse;
import com.scan_app.user_service.dto.UserScanRequest;
import com.scan_app.user_service.dto.filter.FilterScansDto;
import com.scan_app.user_service.service.ScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/scan")
@RequiredArgsConstructor
public class UserScanController {

    @Autowired
    private ScanService scanService;

    @PostMapping("/saveScan")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveScan(@RequestBody UserScanRequest userScanResponse) {
        System.out.println(userScanResponse.scanIp());
        scanService.saveScan(userScanResponse);
        return "Scan saved successfully";
    }

    @GetMapping("/getScansId/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getScansIdByUserId(@PathVariable String userId) {
        return scanService.getScansIdByUserId(userId);
    }

    @GetMapping("/getScans/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserScanResponse> getScansByUserId(@PathVariable String userId) {
        System.out.println(scanService.getScansByUserId(userId).getFirst().elapsedTime());
        return scanService.getScansByUserId(userId);
    }
    //Check that scans belong to user
    @PostMapping("/checkScans")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkScans(@RequestBody CheckScanRequest checkScanRequest) {
        return scanService.doAllScansBelongToUser(checkScanRequest);
    }

    @PostMapping("/deleteScans")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteScans(@RequestBody CheckScanRequest checkScanRequest) {
        return scanService.deleteScans(checkScanRequest);
    }

    @PostMapping("/getFilteredScans")
    @ResponseStatus(HttpStatus.OK)
    public List<UserScanResponse> getFilteredScans(@RequestBody FilterScansDto filterScansDto, @RequestHeader("X-User-ID") String userId) {
        return scanService.getFilteredScans(filterScansDto, userId);
    }

    @PostMapping("/getLatestUserScan")
    @ResponseStatus(HttpStatus.OK)
    public String getUserLatestScan(@RequestBody String userId) {
        return scanService.getUserLatestScan(userId);
    }
}
