package com.scan_app.api_gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerTest {

    @GetMapping("/test/{id}")
    public ResponseEntity<String> getScanById(@PathVariable String id) {
        try {
            return ResponseEntity.ok("Ahoj "+id);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
