package com.scan_app.output_service.dto.scan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HopDto {
    private String ip; //ip address of the hop
    private String rtt; //round trip time
    private String ttl; //time to live
    private String host; //host name
}
