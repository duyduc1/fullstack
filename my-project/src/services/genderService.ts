import apiClient from "@/config/axiosInstance";
import {Gender} from "@/types/Gender";

const API_URL = '/gender';

export const genderService = {
    async getAllGenders(): Promise<Gender[]> {
        const response = await apiClient.get<Gender[]>(API_URL);
        return response.data;
    },

    async saveGender(gender: Gender): Promise<Gender> {
        const response = await apiClient.post<Gender>(API_URL, gender);
        return response.data;
    },

    async getGenderById(id: number): Promise<Gender> {
        const response = await apiClient.get<Gender>(`${API_URL}/${id}`);
        return response.data;
    },

    async updateGender(id: number, gender: Gender): Promise<void> {
        await apiClient.put(`${API_URL}/${id}`, gender);
    },

    async deleteGender(id: number): Promise<void> {
        await apiClient.delete(`${API_URL}/${id}`);
    },
};
