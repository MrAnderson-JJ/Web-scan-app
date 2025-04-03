import { ScanTypes } from "../../../types/scan/scanCommand/ScanType";

export interface ScanResultMessage {
    wenSocketId: string;
    jsonData: string;
    scanType: ScanTypes;
}