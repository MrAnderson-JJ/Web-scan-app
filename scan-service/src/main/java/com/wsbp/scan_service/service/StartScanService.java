package com.wsbp.scan_service.service;

import com.wsbp.scan_service.dto.ScanRequest;
import com.wsbp.scan_service.scanUtil.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class StartScanService {

    private final ScanReportProducer scanReportProducer;
    private final XmlToJsonService xmlToJsonService;

    public void startScanByUser(ScanRequest scanRequest) {
        List<String> options = Command.getFlagsByScanType(scanRequest.getScanType()).getFlags();
        startScanAsync(scanRequest.getIp(), options)
                .thenAccept(xmlOutput ->
                        {
                            System.out.println(xmlOutput);
                            // send this xml output to queue
                            try {
                                String scanOutputToJson = xmlToJsonService.jsonConvert(xmlOutput);
                                System.out.println("scanIP: " + scanRequest.getIp());
                                scanReportProducer.sendMessage(scanRequest.getWebSocketId() ,scanOutputToJson, scanRequest.getScanType(), scanRequest.getUserId(), scanRequest.getIp());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

    }

    public CompletableFuture<String> startScanAsync(String target, List<String> options) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ensureOutputDirectoryExists();
                String[] command = buildCommandXml(target, options);
                System.out.println("Running Nmap command: " + String.join(" ", command));

                ProcessBuilder processBuilder = new ProcessBuilder(command);
                processBuilder.redirectErrorStream(true); // Combine stdout and stderr
                Process process = processBuilder.start();

                // Uložíme XML výstup do proměnné
                StringBuilder xmlOutput = new StringBuilder();
                try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        xmlOutput.append(line).append("\n");
                    }
                }

                int exitCode = process.waitFor();
                String rawXml = xmlOutput.toString().trim();
                if (exitCode == 0) {
                    return xmlOutput.toString().trim(); // Vrátí čisté XML
                } else {
                    throw new Exception("Scan failed with exit code: " + exitCode);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error during scan: " + e.getMessage(), e);
            }
        });
    }

/*    private String buildCommand(String target, List<String> options) {
        // Define the output path for the scan results
        String savePath = "data/scanOutput/scan_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xml";

        // Initialize nmap4j
        Nmap4j nmap4j = new Nmap4j("C:\\Program Files (x86)\\Nmap");

        try {
            // Validate the target
            if (target == null || target.isEmpty()) {
                throw new IllegalArgumentException("Target host cannot be null or empty");
            }
            nmap4j.includeHosts(target);

            String flags = "";
            for (String flag:
                 options) {
                flags = flags + " " + flag;
            }
            // Add Nmap options
            nmap4j.addFlags("-oX " + savePath + " " + flags); // Set output file for XML


            // Execute the Nmap scan
            nmap4j.execute();

            // Debug: Print execution details
            System.out.println("Executed Command: " + nmap4j.getExecutionResults().getExecutedCommand());
            System.out.println("Command Output: " + nmap4j.getExecutionResults().getOutput());
            System.out.println("Errors: " + nmap4j.getExecutionResults().getErrors());

            // Check if the execution was successful
            if (!nmap4j.hasError()) {
                System.out.println(nmap4j.getOutput());
                System.out.println("Scan completed successfully. Results saved to: " + savePath);
            } else {
                System.err.println("Nmap execution error: " + nmap4j.getExecutionResults().getErrors());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(nmap4j.getResult().getXmloutputversion());
        return nmap4j.getResult().getXmloutputversion();
    }*/

    private String[] buildCommandXml(String target, List<String> options) {
        // Generate the save path
        String savePath = "data/scanOutput/scan_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xml";

        // Build the command array
        String[] command = new String[options.size() + 4];
        command[0] = "nmap";
        command[1] = "-oX";
        command[2] = "-"; // Separate entry for the output path
        command[3] = target;   // Target

        for (int i = 0; i < options.size(); i++) {
            command[i + 4] = options.get(i);
        }

        return command;
    }

    private void ensureOutputDirectoryExists() {
        File directory = new File("data/scanOutput");
        if (!directory.exists()) {
            directory.mkdirs(); // Create directories if they don't exist
        }
    }
}
