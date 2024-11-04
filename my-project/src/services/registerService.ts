import apiClient from "@/config/axiosInstance";
import {User} from "@/types/User";

const API_URL = '/auth/signup';

export const registerUser = async (user : User) => {
        const response = await apiClient.post<User>(API_URL , user);
        return response.data
}

