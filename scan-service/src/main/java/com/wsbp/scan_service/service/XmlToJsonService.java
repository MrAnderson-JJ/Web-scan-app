package com.wsbp.scan_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class XmlToJsonService {

    /**
     * Converts XML to JSON
     * @param xmlString nmap output in XML
     * @return nmap output in JSON
     * @throws IOException
     */
    public String jsonConvert(String xmlString) throws IOException {
        // Create an XmlMapper to read XML
        XmlMapper xmlMapper = new XmlMapper();

        // Parse XML string into a JsonNode
        JsonNode jsonNode = xmlMapper.readTree(xmlString);

        // Create an ObjectMapper to write JSON
        ObjectMapper jsonMapper = new ObjectMapper();

        // Convert JsonNode to JSON string
        String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

        // Print JSON output
        System.out.println(json);
        return json;
    }
}
