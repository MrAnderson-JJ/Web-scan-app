package com.scan_app.output_service.dto.scan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scan_app.output_service.entity.Osclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OsMatchDto {
    private String name;
    private String accuracy;
    private String line;
    private List<OsClassDto> osclass;
}
