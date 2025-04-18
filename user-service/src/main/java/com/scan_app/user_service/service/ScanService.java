package com.scan_app.user_service.service;

import com.scan_app.user_service.client.OutputClient;
import com.scan_app.user_service.dto.CheckScanRequest;
import com.scan_app.user_service.dto.UserScanResponse;
import com.scan_app.user_service.dto.UserScanRequest;
import com.scan_app.user_service.dto.filter.FilterScansDto;
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

    private final OutputClient outputClient;

    public void saveScan(UserScanRequest userScanResponse) {
        scanRepository.save(mapToScan(userScanResponse));
    }

    public Scan mapToScan(UserScanRequest userScanResponse) {
        if (userService.userExistsInDb(userScanResponse.userId())) {

            return Scan.builder()
                    .user(userService.getUserById(userScanResponse.userId()))
                    .scanId(userScanResponse.scanId())
                    .scanType(userScanResponse.scanType().name())
                    .scanStartTime(userScanResponse.dateStart())
                    .scanEndTime(userScanResponse.dateEnd())
                    .elapsedTime(userScanResponse.elapsedTime())
                    .active(userScanResponse.active())
                    .scanIp(userScanResponse.scanIp())
                    .build();
        } else {
            throw new IllegalArgumentException("User does not exist in database");
        }
    }

    public List<String> getScansIdByUserId(String userId) {
        return scanRepository.findByUserId(userId).stream().map(Scan::getScanId).toList();
    }

    public List<UserScanResponse> getScansByUserId(String userId) {
        List<UserScanResponse> scansResponse = new ArrayList<>();
        List<Scan> scansEntity = scanRepository.findByUserId(userId);
        for (Scan scan: scansEntity) {
            scansResponse.add(mapScanToRequest(scan));
        }
        return scansResponse;
    }

    public UserScanResponse mapScanToRequest(Scan scan) {
        ScanTypes scanType = ScanTypes.valueOf(scan.getScanType());
        return new UserScanResponse(scan.getScanId(), scanType, scan.getScanStartTime(), scan.getScanEndTime(), scan.getElapsedTime(), scan.getScanIp());
    }

    public List<Scan> getScansByScanIds(List<String> scanIds) {
        return scanRepository.findByScanIdIn(scanIds);
    }

    public boolean deleteScans(CheckScanRequest checkScanRequest) {
        scanRepository.deleteAllById(checkScanRequest.scanIds());
        return scanRepository.findAllById(checkScanRequest.scanIds()).isEmpty();
    }

    public boolean doAllScansBelongToUser(CheckScanRequest checkScanRequest) {
        List<String> scanIds = checkScanRequest.scanIds();
        List<Scan> scans = scanRepository.findByScanIdIn(scanIds);

        return scans.size() == scanIds.size() && scans.stream().allMatch(scan -> scan.getUser().getId().equals(checkScanRequest.userId()));
    }

    public List<UserScanResponse> getFilteredScans(FilterScansDto filterScansDto, String userId) {
        filterScansDto.setScanIds(getScansIdByUserId(userId));
        List<String> filteredScans = outputClient.getFilteredScanIds(filterScansDto);

        return getScansByScanIds(filteredScans).stream().map(this::mapScanToRequest).toList();
    }

    public String getUserLatestScan(String userId) {
        return scanRepository.findTopByUser_IdOrderByScanEndTimeDesc(userId)
                .map(Scan::getScanId)
                .orElse(null);
    }
}
