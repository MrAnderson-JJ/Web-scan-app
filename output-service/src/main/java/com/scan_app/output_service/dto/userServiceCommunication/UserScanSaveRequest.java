package com.scan_app.output_service.dto.userServiceCommunication;

import com.scan_app.output_service.scanUtil.ScanTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserScanSaveRequest {
    private String scanId;
    private String userId;
    private ScanTypes scanType;
}
