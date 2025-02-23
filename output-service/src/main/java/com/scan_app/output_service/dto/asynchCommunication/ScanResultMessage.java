package com.scan_app.output_service.dto.asynchCommunication;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScanResultMessage {
    @NonNull
    private String webSocketId;
    private String jsonData;
    private String scanType;
}
