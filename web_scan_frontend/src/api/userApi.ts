import { axiosInstanceApiGateway as api } from "./axiosConfig";
import { CheckScanRequest, FilterScansDto, UserScan } from "@/types";

// Function to fetch ports by scan ID
export const saveUserIfNotExists = async (userId: string): Promise<Boolean> => {
  try {
    console.log(userId);
    const response = await api.post<Boolean>(`/user/save`, { userId });
    return response.data;
  } catch (error) {
    console.error("Error fetching user:", error);
    throw error;
  }
};

export const fetchUserScans = async (userId: string): Promise<UserScan[]> => {
  try {
    const response = await api.get<UserScan[]>(`/user/scan/getScans/${userId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching userScans:", error);
    throw error;
  }
};

export const deleteUserScans = async (userId: string, scanIds: string[]): Promise<string> => {
  try {
    const response = await api.post<string>(`/output/scans/delete`, { userId, scanIds });
    return response.data;
  } catch (error) {
    console.error("Error fetching userScans:", error);
    throw error;
  }
};

export const fetchFilteredScans = async (filterScansDto: FilterScansDto): Promise<UserScan[]> => {
  try {
    const response = await api.post<UserScan[]>(`/user/scan/getFilteredScans`, filterScansDto);
    return response.data;
  } catch (error) {
    console.error("Error fetching userScans:", error);
    throw error;
  }
};