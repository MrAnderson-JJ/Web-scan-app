import { ScanTypes } from "@/types/ScanType";
import { axiosInstanceScan as api } from "./axiosConfig";

// Function to fetch ports by scan ID
export const startScan = async (ip: string, options: string[], scanType: ScanTypes): Promise<string> => {
  try {
    console.log(ip, options);
    const response = await api.post<string>(`/send`, {ip, options});
    return response.data;
  } catch (error) {
    console.error("Error fetching scan message:", error);
    throw error; // Propagate the error to the caller
  }
};