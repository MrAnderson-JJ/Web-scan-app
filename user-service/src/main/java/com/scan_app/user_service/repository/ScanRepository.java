package com.scan_app.user_service.repository;

import com.scan_app.user_service.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScanRepository extends JpaRepository<Scan, String> {
    List<Scan> findByUserId(String userId);
}
