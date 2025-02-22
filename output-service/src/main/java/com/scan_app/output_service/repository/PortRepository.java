package com.scan_app.output_service.repository;

import com.scan_app.output_service.entity.Ports;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PortRepository extends MongoRepository<Ports, String> {
    Ports findByHostRefIdIn(List<String> hostIds);
}
