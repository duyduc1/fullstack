import apiClient from "@/config/axiosInstance";
import { LoginRequest , LoginResponse } from "@/types/User";

const API_URL = '/auth/signin';

export const loginUser = async (loginRequest: LoginRequest):Promise<LoginResponse> => {
    const response = await apiClient.post(API_URL,loginRequest)
    return response.data
}