package com.scan_app.output_service.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class ConverterDate {

    public static Date convertStringToDate(Long date) {
        Long timestamp = date;
        System.out.println("timestamp: " + timestamp);
        return new Date(timestamp * 1000);
    }
}
