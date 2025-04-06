package com.wsbp.scan_service.dto;

import com.wsbp.scan_service.scanUtil.ScanTypes;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScanRequest {
    @NonNull
    private String ip;
    private String userId;
    private String scanTiming;
    private ScanTypes scanType;
    private String portRange;
    private String webSocketId;
}
