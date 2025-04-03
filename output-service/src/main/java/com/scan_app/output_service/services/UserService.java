package com.scan_app.output_service.services;

import com.scan_app.output_service.client.UserClient;
import com.scan_app.output_service.dto.scan.NmapRunDto;
import com.scan_app.output_service.dto.userServiceCommunication.CheckScanRequest;
import com.scan_app.output_service.dto.userServiceCommunication.UserScanSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ScanService scanService;

    private final UserClient userClient;

    public List<NmapRunDto> getNmapRunsByUserId(String userId) {
        List<String> scans = userClient.getScansByUserId(userId);
        List<NmapRunDto> nmapRunDtos = new ArrayList<>();
        for (String scan : scans){
            nmapRunDtos.add(scanService.getNmapRunByScanId(scan));
        }

        return nmapRunDtos;
    }

    public NmapRunDto getLatestUserScan(String userId) {
        String latestScan = userClient.getLatestUserScan(userId);
        return scanService.getNmapRunByScanId(latestScan);
    }

    public boolean scansBelongToUser(CheckScanRequest checkScanRequest) {
        return userClient.scansBelongToUser(checkScanRequest);
    }

    public void deleteScans(CheckScanRequest checkScanRequest) {
        userClient.deleteScans(checkScanRequest);
    }

    public void saveScan(UserScanSaveRequest userScanSaveRequest) {
        userClient.saveScan(userScanSaveRequest);
    }
}
