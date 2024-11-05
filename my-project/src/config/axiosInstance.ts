import axios, { AxiosInstance } from "axios";
import config from "@/config/config";

const apiClient: AxiosInstance = axios.create({
    baseURL: config.apiBaseUrl,
    headers: {
        'Content-Type': 'application/json',
    },
});

apiClient.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        
        if (config.url === '/auth/signin' || config.url === '/auth/signup' || config.url === '/company' || config.url === '/gender' ) {
            return config;
        }
        if (!token) {
            return Promise.reject(new Error("Token không tồn tại. Vui lòng đăng nhập lại."));
        }
        
        config.headers.Authorization = `Bearer ${token}`;
        return config;
    },
    (error) => Promise.reject(error)
);

export default apiClient;
