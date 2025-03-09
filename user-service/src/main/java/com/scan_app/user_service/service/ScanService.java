package com.scan_app.user_service.service;

import com.scan_app.user_service.dto.UserScanRequest;
import com.scan_app.user_service.dto.UserScanResponse;
import com.scan_app.user_service.model.Scan;
import com.scan_app.user_service.repository.ScanRepository;
import com.scan_app.user_service.util.ScanTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for handling user scan requests
 * Like saving scan request to database with user id so that user scan history can be maintained
 */
@Service
@RequiredArgsConstructor
public class ScanService {

    @Autowired
    private ScanRepository scanRepository;
    @Autowired
    private UserService userService;

    public void saveScan(UserScanResponse userScanResponse) {
        scanRepository.save(mapToScan(userScanResponse));
    }

    public Scan mapToScan(UserScanResponse userScanResponse) {
        if (userService.userExistsInDb(userScanResponse.userId())) {

        Scan scan = Scan.builder()
                .user(userService.getUserById(userScanResponse.userId()))
                .scanId(userScanResponse.scanId())
                .scanType(userScanResponse.scanType().name())
                .build();

        return scan;
        } else {
            throw new IllegalArgumentException("User does not exist in database");
        }
    }

    public List<String> getScansIdByUserId(String userId) {
        return scanRepository.findByUserId(userId).stream().map(Scan::getScanId).toList();
    }

    public List<UserScanRequest> getScansByUserId(String userId) {
        List<UserScanRequest> scansResponse = new ArrayList<>();
        List<Scan> scansEntity = scanRepository.findByUserId(userId);
        for (Scan scan: scansEntity) {
            scansResponse.add(mapScanToRequest(scan));
        }
        return scansResponse;
    }

    public UserScanRequest mapScanToRequest(Scan scan) {
        ScanTypes scanType = ScanTypes.valueOf(scan.getScanType());
        return new UserScanRequest(scan.getScanId(), scanType);
    }
}
