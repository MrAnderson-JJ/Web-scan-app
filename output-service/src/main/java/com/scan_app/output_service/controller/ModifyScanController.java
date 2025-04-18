package com.scan_app.output_service.controller;

import com.scan_app.output_service.dto.userServiceCommunication.CheckScanRequest;
import com.scan_app.output_service.services.UserScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/output/scans")
public class ModifyScanController {

    @Autowired
    private UserScanService userScanService;

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteScans(@RequestBody CheckScanRequest checkScanRequest) {
        try {
            userScanService.deleteScans(checkScanRequest);
            return "Scans deleted successfully";
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
