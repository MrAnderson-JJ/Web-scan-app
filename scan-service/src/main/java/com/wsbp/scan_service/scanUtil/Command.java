package com.wsbp.scan_service.scanUtil;

import java.util.ArrayList;
import java.util.List;

public class Command {

    public static Flags getFlagsByScanType(ScanTypes scanType){
        Flags flags = new Flags();
        switch (scanType){
            case SCAN_PING:
                flags.addFlag("sn");
                return flags;
            case SCAN_QUICK:
                flags.addFlag("T4");
                flags.addFlag("F");
                return flags;
            case SCAN_FULL:
                flags.addFlag("T4");
                flags.addFlag("A");
                flags.addFlag("v");
                return flags;
            default:
                return flags;
        }
    }
}
