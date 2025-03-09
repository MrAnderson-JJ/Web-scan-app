package com.wsbp.scan_service.dto;

import com.wsbp.scan_service.scanUtil.ScanTypes;
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
