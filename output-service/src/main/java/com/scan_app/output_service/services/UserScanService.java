package com.scan_app.output_service.services;

import com.scan_app.output_service.dto.userServiceCommunication.CheckScanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserScanService {

    private final ScanService scanService;

    private final UserService userService;

    public void deleteScans(CheckScanRequest checkScanRequest) {
        if (!userService.scansBelongToUser(checkScanRequest)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Scans do not belong to user");
        }
        try {
            scanService.deleteScans(checkScanRequest.scanIds());
            userService.deleteScans(checkScanRequest);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
