package com.wsbp.scan_service.dto;

import com.wsbp.scan_service.scanUtil.ScanTypes;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScanResultMessage {
    @NonNull
    private String webSocketId;
    private String jsonData;
    private ScanTypes scanType;
}
