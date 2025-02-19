package com.scan_app.output_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HostStatusDto {
    private String reason;
    private String reasonTtl;
    private String state;
}
