import { ScanTypes } from "../../../types/ScanType";

export interface ScanResultMessage {
    wenSocketId: string;
    jsonData: string;
    scanTypes: ScanTypes;
}