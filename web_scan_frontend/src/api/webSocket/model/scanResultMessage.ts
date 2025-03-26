import { ScanTypes } from "../../../types/scan/ScanType";

export interface ScanResultMessage {
    wenSocketId: string;
    jsonData: string;
    scanType: ScanTypes;
}