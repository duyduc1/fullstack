import apiClient from "@/config/axiosInstance";
import { User } from "@/types/User";

const API_URL = '/users';

export const userService = {
    async getAllUsers(): Promise<User[]> {
        const response = await apiClient.get<User[]>(API_URL);
        return response.data;
    },

    async saveUser(user: User): Promise<User> {
        const response = await apiClient.post<User>(API_URL, user);
        return response.data;
    },

    async getUserById(id: number): Promise<User> {
        const response = await apiClient.get<User>(`${API_URL}/${id}`);
        return response.data;
    },

    async updateUser(id: number, user: User): Promise<void> {
        await apiClient.put(`${API_URL}/${id}`, user);
    },

    async deleteUser(id: number): Promise<void> {
        await apiClient.delete(`${API_URL}/${id}`);
    },
};
