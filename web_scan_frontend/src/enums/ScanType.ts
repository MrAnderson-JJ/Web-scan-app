export enum ScanTypes {
    SCAN_PING = "SCAN_PING",
    SCAN_QUICK = "SCAN_QUICK",
    SCAN_FULL = "SCAN_FULL",
}

export const ScanTypeLabels: Record<ScanTypes, string> = {
    [ScanTypes.SCAN_PING]: "Ping Scan (Check if Host is Up)",
    [ScanTypes.SCAN_QUICK]: "Port Scan (Scan ports - default 100 most common)",
    [ScanTypes.SCAN_FULL]: "Full Scan (Ports, OS detection, Traceroute)",
};