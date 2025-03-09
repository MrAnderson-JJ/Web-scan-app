package com.scan_app.user_service.controller;

import com.scan_app.user_service.dto.UserScanRequest;
import com.scan_app.user_service.dto.UserScanResponse;
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
    public String saveScan(@RequestBody UserScanResponse userScanResponse) {
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
    public List<UserScanRequest> getScansByUserId(@PathVariable String userId) {
        return scanService.getScansByUserId(userId);
    }
}
