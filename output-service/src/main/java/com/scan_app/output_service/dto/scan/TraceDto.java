package com.scan_app.output_service.dto.scan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TraceDto {
    private String port;
    private String proto;
    private List<HopDto> hop;
}
