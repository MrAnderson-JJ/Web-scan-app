package com.scan_app.output_service.dto;

import com.scan_app.output_service.entity.Host;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PingDto {
    private HostDto host;
    private String elapsed; //scan time
}
