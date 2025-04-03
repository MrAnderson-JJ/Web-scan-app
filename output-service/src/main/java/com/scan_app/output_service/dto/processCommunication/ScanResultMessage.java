package com.scan_app.output_service.dto.processCommunication;

import com.scan_app.output_service.scanUtil.ScanTypes;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScanResultMessage {
    @NonNull
    private String webSocketId;
    private String jsonData;
    private ScanTypes scanType;
    private String userId;
    private String scanIp;
}
