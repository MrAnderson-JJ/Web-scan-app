package com.scan_app.user_service.repository;

import com.scan_app.user_service.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanRepository extends JpaRepository<Scan, Long> {
}
