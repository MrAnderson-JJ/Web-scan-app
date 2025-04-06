import { ScanTypes } from "../scan/scanCommand/ScanType";

export interface FilterScansDto {
    port?: number | null;
    maxDistance?: number | null;
    maxOpenPorts?: number | null;
    oneHost?: boolean;
}