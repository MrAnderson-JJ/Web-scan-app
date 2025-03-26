package com.scan_app.output_service.dto;

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
}
