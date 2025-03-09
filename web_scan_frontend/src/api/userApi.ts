import { axiosInstanceApiGateway as api } from "./axiosConfig";
import { UserScan } from "@/types";

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