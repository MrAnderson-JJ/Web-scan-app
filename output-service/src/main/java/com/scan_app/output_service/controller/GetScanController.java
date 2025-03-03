package com.scan_app.output_service.controller;

import com.scan_app.output_service.dto.scan.HostDto;
import com.scan_app.output_service.dto.scan.PingDto;
import com.scan_app.output_service.dto.scan.PortDto;
import com.scan_app.output_service.entity.Nmaprun;
import com.scan_app.output_service.services.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/output/scans")
public class GetScanController {

    @Autowired
    private ScanService scanService;

    //upload scan by xml file path
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Nmaprun> uploadScan(@RequestBody String path) {
        try {
            Nmaprun savedScan = scanService.saveScan(path);
            return ResponseEntity.ok(savedScan);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nmaprun> getScanById(@PathVariable String id) {
        try {
            Nmaprun scan = scanService.getScanById(id);
            return ResponseEntity.ok(scan);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/getports/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PortDto> getPortsByScanId(@PathVariable String id) {
            List<PortDto> scan = scanService.getPortsByScanId(id);
            System.out.println("controller scan: " + scan.getFirst().getPortId());
            return scan;
    }

    @GetMapping("/gethostsquick/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HostDto> getHostsQuickByScanId(@PathVariable String id) {
        List<HostDto> scan = scanService.getQuickScanByScanId(id);
        System.out.println("controller scan: " + scan.getFirst().getAddress().getAddr());
        return scan;
    }

    @GetMapping("/getping/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PingDto getPingByScanId(@PathVariable String id) {
        PingDto pingResult = scanService.getPingByScanId(id);
        System.out.println("controller ping scan: ");
        return pingResult;
    }

    @GetMapping("/getintense/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HostDto> getIntenseScanByScanId(@PathVariable String id) {
        List<HostDto> scan = scanService.getQuickScanByScanId(id);
        System.out.println("controller scan: " + scan.getFirst().getAddress().getAddr());
        return scan;
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<String> getScanB(@PathVariable String id) {
        try {
            return ResponseEntity.ok("Ahoj "+id);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
