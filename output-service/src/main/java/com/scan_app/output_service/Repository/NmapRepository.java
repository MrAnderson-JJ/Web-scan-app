package com.scan_app.output_service.Repository;

import com.scan_app.output_service.entity.Nmaprun;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NmapRepository extends MongoRepository<Nmaprun, String> {
    List<Nmaprun> findByScanner(String scanner);

    @Query(value = "{ '_id': ?0 }", fields = "{ 'host.ports.port': 1, '_id': 0 }")
    Nmaprun findPortsByScanId(String scanId);
}
