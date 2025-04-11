import { ScanTypes } from "../../enums/ScanType";

export interface FilterScansDto {
    port?: number | null;
    maxDistance?: number | null;
    maxOpenPorts?: number | null;
    oneHost?: boolean;
}