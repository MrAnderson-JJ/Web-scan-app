package com.scan_app.output_service.dto.scan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OsClassDto {
    private String type;
    private String vendor;
    private String osfamily;
    private String accuracy;
    private String cpe;
}
