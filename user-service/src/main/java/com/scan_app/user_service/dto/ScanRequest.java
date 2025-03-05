package com.scan_app.user_service.dto;

import com.scan_app.user_service.util.ScanTypes;

import java.math.BigDecimal;

public record ScanRequest(Long scanId, ScanTypes scanType) {
}
