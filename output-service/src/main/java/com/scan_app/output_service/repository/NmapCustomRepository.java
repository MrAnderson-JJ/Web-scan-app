package com.scan_app.output_service.repository;

import com.scan_app.output_service.entity.Nmaprun;

import java.util.List;

public interface NmapCustomRepository {
    List<Nmaprun> filterScans(List<String> scanIds, Integer port, Integer maxDistance);

}
