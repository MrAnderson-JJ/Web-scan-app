import { ScanTypes } from "../../../enums/ScanType";

export interface ScanResultMessage {
    wenSocketId: string;
    jsonData: string;
    scanType: ScanTypes;
}