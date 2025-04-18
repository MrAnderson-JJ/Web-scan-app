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
public class HostDto {
    private HostStatusDto hostStatusDto;
    private AddressDto address;
    private List<PortDto> ports;
    private TraceDto trace;
    private OsDto os;
}
