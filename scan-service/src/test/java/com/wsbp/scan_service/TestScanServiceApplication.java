package com.wsbp.scan_service;

import org.springframework.boot.SpringApplication;

public class TestScanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ScanServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
