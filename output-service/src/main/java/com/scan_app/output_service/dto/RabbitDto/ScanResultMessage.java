package com.scan_app.output_service.dto.RabbitDto;

import com.scan_app.output_service.scanUtil.ScanTypes;
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
}
