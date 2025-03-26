package com.scan_app.output_service.services;

import com.scan_app.output_service.dto.userServiceCommunication.CheckScanRequest;
import com.scan_app.output_service.dto.userServiceCommunication.UserScanSaveRequest;
import com.scan_app.output_service.entity.Nmaprun;
import com.scan_app.output_service.scanUtil.ScanTypes;
import com.scan_app.output_service.util.ConverterDate;
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

    public void saveScan(String userId , String scanIp, Nmaprun nmaprun, ScanTypes scanType) {
        userService.saveScan(mapToUserScanSaveRequest(userId, scanIp, nmaprun, scanType));
    }

    public UserScanSaveRequest mapToUserScanSaveRequest(String userId , String scanIp, Nmaprun nmaprun, ScanTypes scanType) {
        System.out.println(ConverterDate.convertStringToDate(nmaprun.getStart()));
        return UserScanSaveRequest.builder()
                .userId(userId)
                .scanId(nmaprun.get_id())
                .scanIp(scanIp)
                .scanType(scanType)
                .dateStart(ConverterDate.convertStringToDate(nmaprun.getStart()))
                .dateEnd(ConverterDate.convertStringToDate(nmaprun.getRunstats().getFinished().getTime()))
                .elapsedTime(Double.valueOf(nmaprun.getRunstats().getFinished().getElapsed()))
                .active(true)
                .build();
    }
}
