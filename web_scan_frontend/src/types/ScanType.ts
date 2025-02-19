export enum ScanTypes {
    SCAN_PING = "SCAN_PING",
    SCAN_QUICK = "SCAN_QUICK",
    SCAN_FULL = "SCAN_FULL",
}

export const ScanTypeLabels: Record<ScanTypes, string> = {
    [ScanTypes.SCAN_PING]: "Ping Scan (Check if Host is Up)",
    [ScanTypes.SCAN_QUICK]: "Quick Scan (Fast and Basic Ports)",
    [ScanTypes.SCAN_FULL]: "Full Scan (Deep Port Analysis)",
};