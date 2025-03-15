package com.scan_app.user_service.dto;

import java.util.List;

public record CheckScanRequest(String userId, List<String> scanIds) {
}
