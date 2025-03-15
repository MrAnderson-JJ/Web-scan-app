package com.scan_app.output_service.dto.userServiceCommunication;

import java.util.List;

public record CheckScanRequest(String userId, List<String> scanIds) {
}
