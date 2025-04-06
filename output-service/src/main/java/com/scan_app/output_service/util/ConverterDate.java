package com.scan_app.output_service.util;

import java.util.Date;


public class ConverterDate {

    public static Date convertStringToDate(Long date) {
        return new Date(date * 1000);
    }
}
