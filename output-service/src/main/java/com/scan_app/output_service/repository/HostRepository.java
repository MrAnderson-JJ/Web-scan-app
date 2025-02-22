package com.scan_app.output_service.repository;

import com.scan_app.output_service.entity.Host;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HostRepository extends MongoRepository<Host, String> {
    List<Host> findByNmapRunRefId(String nmapRunId);
}
