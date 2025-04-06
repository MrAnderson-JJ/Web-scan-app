package com.scan_app.user_service.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterScansDto {
    private List<String> scanIds;
    private Integer port;
    private Integer maxDistance;
    private Integer maxOpenPorts;
    private Boolean oneHost;
}

