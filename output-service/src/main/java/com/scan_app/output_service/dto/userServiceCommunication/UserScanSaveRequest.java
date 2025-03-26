package com.scan_app.output_service.dto.userServiceCommunication;

import com.scan_app.output_service.scanUtil.ScanTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserScanSaveRequest {
    private String scanId;
    private String userId;
    private String scanIp;
    private ScanTypes scanType;
    private Date dateStart;
    private Date dateEnd;
    private Double elapsedTime;
    private boolean active;
}
