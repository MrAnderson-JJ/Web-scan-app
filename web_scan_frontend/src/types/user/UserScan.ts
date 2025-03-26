import { ScanTypes } from "../scan/ScanType";

export interface UserScan {
    scanId: string;
    scanType: ScanTypes;
    dateStart: Date;
    dateEnd: Date;
    elapsedTime: number;
    scanIp: string;
}