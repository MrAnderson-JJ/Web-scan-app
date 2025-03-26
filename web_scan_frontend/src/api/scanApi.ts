import { axiosInstanceApiGateway as api } from "./axiosConfig";
import { Host, Port } from "../types";
import { Ping } from "@/types/scan/Ping";
import { ScanTypes } from "@/types/scan/ScanType";

// Function to fetch ports by scan ID
export const fetchPortsByScanId = async (scanId: string): Promise<Port[]> => {
  try {
    const response = await api.get<Port[]>(`/output/scans/getports/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

// Function to fetch ports by scan ID
export const fetchQuick = async (scanId: string): Promise<Host[]> => {
  try {
    const response = await api.get<Host[]>(`/output/scans/gethostsquick/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

export const fetchPing = async (scanId: string): Promise<Ping> => {
  try {
    const response = await api.get<Ping>(`/output/scans/getping/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

export const fetchIntense = async (scanId: string): Promise<Host[]> => {
  try {
    const response = await api.get<Host[]>(`/output/scans/getintense/${scanId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};

export const startScanFromOutput = async (ip: string, userId: string, options: string[], scanType: ScanTypes): Promise<string> => {
  try {
    console.log(ip, options, scanType);
    const response = await api.post<string>(`/output/scan/send`, {ip, userId, options, scanType});
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching scan message:", error);
    throw error; // Propagate the error to the caller
  }
};

export const fetchTest = async (id: string): Promise<string> => {
  try {
    const response = await api.get<string>(`output/scans/test/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching ports:", error);
    throw error; // Propagate the error to the caller
  }
};