package com.scan_app.user_service.dto;

import com.scan_app.user_service.util.ScanTypes;
import lombok.Builder;

import java.util.Date;

@Builder
public record UserScanResponse(String scanId, ScanTypes scanType, Date dateStart, Date dateEnd, Double elapsedTime, String scanIp) {
}
