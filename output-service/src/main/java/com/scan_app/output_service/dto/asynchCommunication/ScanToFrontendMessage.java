package com.scan_app.output_service.dto.asynchCommunication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScanToFrontendMessage {
    @NonNull
    private String scanId;
    private ScanResultMessage scanResultMessage;
}
