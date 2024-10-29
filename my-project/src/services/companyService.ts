import apiClient from "@/config/axiosInstance";
import {Company} from "@/types/Company";

const  API_URL = '/company'

export const companyService = {
    async getAllCompany(): Promise<Company[]> {
        const response = await apiClient.get<Company[]>(API_URL);
        return response.data
    },

    async saveCompany(company : Company):Promise<Company> {
        const reponse = await apiClient.post<Company>(API_URL , company)
        return reponse.data
    },

    async getCompanyById(id: number):Promise<Company> {
        const response = await apiClient.get<Company>(`${API_URL}/${id}`);
        return response.data
    },

    async updateCompany(id: number , company : Company): Promise<void> {
        await apiClient.put(`${API_URL}/${id}` , company);
    },

    async deleteCompany(id: number): Promise<void> {
        await apiClient.delete(`${API_URL}/${id}`)
    },
}

