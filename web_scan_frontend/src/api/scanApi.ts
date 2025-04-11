import { axiosInstanceApiGateway as api } from "./axiosConfig";
import { Host, NmapRunDto } from "../dtos";
import { ScanTypes } from "@/enums/ScanType";
import { ScanTiming } from "@/enums/ScanTiming";

export const fetchIntense = async (scanId: string): Promise<Host[]> => {
  try {
    const response = await api.get<Host[]>(`/output/scans/getintense/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

export const startScanFromOutput = async (ip: string, userId: string, scanTiming: ScanTiming, scanType: ScanTypes, portRange: String): Promise<string> => {
  try {
    console.log(ip, scanTiming, scanType, portRange);
    const response = await api.post<string>(`/output/scan/send`, {ip, userId, scanTiming, scanType, portRange});
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching scan message:", error);
    throw error; // Propagate the error to the caller
  }
};

export const fetchLastScan = async (): Promise<NmapRunDto> => {
  try {
    const response = await api.get<NmapRunDto>(`/output/scans/getLatestUserScan`);
    return response.data;
  } catch (error) {
    console.error("Error fetching last scan:", error);
    throw error;
  }
};