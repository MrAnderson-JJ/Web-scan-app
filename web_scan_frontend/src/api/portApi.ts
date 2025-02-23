import { axiosInstance as api } from "./axiosConfig";
import { Host, Port } from "../types";
import { Ping } from "@/types/Ping";
import { ScanTypes } from "@/types/ScanType";

// Function to fetch ports by scan ID
export const fetchPortsByScanId = async (scanId: string): Promise<Port[]> => {
  try {
    const response = await api.get<Port[]>(`/scans/getports/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

// Function to fetch ports by scan ID
export const fetchQuick = async (scanId: string): Promise<Host[]> => {
  try {
    const response = await api.get<Host[]>(`/scans/gethostsquick/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

export const fetchPing = async (scanId: string): Promise<Ping> => {
  try {
    const response = await api.get<Ping>(`/scans/getping/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

export const fetchIntense = async (scanId: string): Promise<Host[]> => {
  try {
    const response = await api.get<Host[]>(`/scans/getintense/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

export const startScanFromOutput = async (ip: string, options: string[], scanType: ScanTypes): Promise<string> => {
  try {
    console.log(ip, options, scanType);
    const response = await api.post<string>(`/scan/send`, {ip, options, scanType});
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching scan message:", error);
    throw error; // Propagate the error to the caller
  }
};