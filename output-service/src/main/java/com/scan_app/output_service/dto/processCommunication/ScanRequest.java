package com.scan_app.output_service.dto.processCommunication;

import com.scan_app.output_service.scanUtil.ScanTypes;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScanRequest {
    @NonNull
    private String ip;
    private String userId;
    private List<String> options;
    private ScanTypes scanType;
    private String webSocketId;
}
