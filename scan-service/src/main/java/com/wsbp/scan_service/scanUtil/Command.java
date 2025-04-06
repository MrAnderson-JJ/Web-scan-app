package com.wsbp.scan_service.scanUtil;

public class Command {

    public static Flags getFlagsByScanType(ScanTypes scanType){
        Flags flags = new Flags();
        switch (scanType){
            case SCAN_PING:
                flags.addFlag("sn");
                return flags;
            case SCAN_QUICK:
                return flags;
            case SCAN_FULL:
                flags.addFlag("A");
                flags.addFlag("v");
                return flags;
            default:
                return flags;
        }
    }
}
