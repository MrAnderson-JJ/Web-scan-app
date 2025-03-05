import { axiosInstanceApiGateway as api } from "./axiosConfig";
import { Host, Port } from "../types";
import { Ping } from "@/types/Ping";
import { ScanTypes } from "@/types/ScanType";

// Function to fetch ports by scan ID
export const saveUserIfNotExists = async (userId: string): Promise<Boolean> => {
  try {
    const response = await api.post<Boolean>(`/user/save/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching user:", error);
    throw error;
  }
};