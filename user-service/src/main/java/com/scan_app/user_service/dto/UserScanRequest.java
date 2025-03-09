package com.scan_app.user_service.dto;

import com.scan_app.user_service.util.ScanTypes;
import lombok.Builder;

@Builder
public record UserScanRequest(String scanId, ScanTypes scanType) {
}
