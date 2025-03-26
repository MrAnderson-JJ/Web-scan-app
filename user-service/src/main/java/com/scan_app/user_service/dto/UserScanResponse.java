package com.scan_app.user_service.dto;

import com.scan_app.user_service.util.ScanTypes;

import java.util.Date;

public record UserScanResponse(String scanId, String userId, ScanTypes scanType, Date dateStart, Date dateEnd, Double elapsedTime, boolean active, String scanIp) {
}
