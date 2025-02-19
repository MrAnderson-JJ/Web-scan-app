package com.scan_app.output_service.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class XmlToJsonService {

    public String jsonConvert(String filePath) throws IOException {
            // Input XML file
            File xmlFile = new File(filePath); // Replace with your XML file path

            // Create an XmlMapper to read XML
            XmlMapper xmlMapper = new XmlMapper();

            // Parse XML into a JsonNode
            JsonNode jsonNode = xmlMapper.readTree(xmlFile);

            // Create an ObjectMapper to write JSON
            ObjectMapper jsonMapper = new ObjectMapper();

            // Convert JsonNode to JSON string
            String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // Print JSON output
            System.out.println(json);
            return json;
    }

    public String jsonStringConvert(String xmlString) throws IOException {
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
