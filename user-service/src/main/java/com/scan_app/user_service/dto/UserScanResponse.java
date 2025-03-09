package com.scan_app.user_service.dto;

import com.scan_app.user_service.util.ScanTypes;

public record UserScanResponse(String scanId, String userId, ScanTypes scanType) {
}
