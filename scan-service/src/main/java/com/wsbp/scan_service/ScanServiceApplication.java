package com.wsbp.scan_service;

import com.wsbp.scan_service.service.StartScanService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ScanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScanServiceApplication.class, args);
/*		StartScanService startScanService = new StartScanService();
		List<String> options = List.of("-v");
		startScanService.startScanAsync("scanme.nmap.org",options);*/
	}

}
