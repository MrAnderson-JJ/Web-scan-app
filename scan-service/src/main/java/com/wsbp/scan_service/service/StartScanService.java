package com.wsbp.scan_service.service;

import com.wsbp.scan_service.dto.ScanRequest;
import com.wsbp.scan_service.scanUtil.Command;
import com.wsbp.scan_service.scanUtil.Flags;
import com.wsbp.scan_service.scanUtil.ScanTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class StartScanService {

    private final ScanReportProducer scanReportProducer;
    private final XmlToJsonService xmlToJsonService;

    /**
     * Starts nmap scan and return its output to message queue if nmap doesnt fail
     * before it builds List of nmap options given in params
     * @param scanRequest request with needed params to start scan (ip, options..)
     */
    public void startScanByUser(ScanRequest scanRequest) {
        // create options for nmap scan
        Flags flags = Command.getFlagsByScanType(scanRequest.getScanType());
        if (scanRequest.getScanType()!= ScanTypes.SCAN_PING) {
            flags.addFlag(scanRequest.getScanTiming());
            if (!Objects.equals(scanRequest.getPortRange(), "")) {
                flags.addFlag(scanRequest.getPortRange());
            }
        }
        List<String> options = flags.getFlags();
        startScanAsync(scanRequest.getIp(), options)
                .thenAccept(xmlOutput ->
                        {
                            System.out.println(xmlOutput);
                            if (xmlOutput == null) {
                                scanReportProducer.sendMessage(scanRequest.getWebSocketId() ,null, scanRequest.getScanType(), scanRequest.getUserId(), scanRequest.getIp());
                            }
                            // send this xml output to queue
                            try {
                                String scanOutputToJson = xmlToJsonService.jsonConvert(xmlOutput);
                                scanReportProducer.sendMessage(scanRequest.getWebSocketId() ,scanOutputToJson, scanRequest.getScanType(), scanRequest.getUserId(), scanRequest.getIp());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

    }

    /**
     * Asynch functin that starts nmap scanning using given params
     * @param target target ip address to be scanned
     * @param options options for nmap scan (T1, sn, ..) - tels nmap how to scan
     * @return raw XML output of scan if nmap succeded, null if nmap started but failed
     * because of wrong parameters (wrong ip address etc.)
     * and throws exception if proccess fails
     */
    public CompletableFuture<String> startScanAsync(String target, List<String> options) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String[] command = buildCommandXml(target, options);

                ProcessBuilder processBuilder = new ProcessBuilder(command);
                processBuilder.redirectErrorStream(true); // Combine stdout and stderr
                Process process = processBuilder.start();

                // Save XML output to variable
                StringBuilder xmlOutput = new StringBuilder();
                try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        xmlOutput.append(line).append("\n");
                        // Kill proccess and retutn null if scan fails (Wrong command etc.)
                        if (line.contains("exit=\"error\"")) {
                            process.destroyForcibly();
                            return null;
                        }
                    }
                }


                int exitCode = process.waitFor();
                String rawXml = xmlOutput.toString().trim();

                // If proccess is completed, return raw XML output
                if (exitCode == 0) {
                    return rawXml;
                } else {
                    throw new Exception("Scan failed with exit code: " + exitCode);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error during scan: " + e.getMessage(), e);
            }
        });
    }

    /**
     * Builds nmap command using given ip address and scan options
     * @param target ip address to be scanned
     * @param options options for nmap scan (T1, sn, ..) - tels nmap how to scan
     * @return full nmap command
     */
    private String[] buildCommandXml(String target, List<String> options) {

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
}
