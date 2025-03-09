package com.scan_app.output_service.services;

import com.scan_app.output_service.client.UserClient;
import com.scan_app.output_service.dto.scan.HostDto;
import com.scan_app.output_service.dto.scan.NmapRunDto;
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
}
