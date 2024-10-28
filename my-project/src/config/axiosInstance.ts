import axios , {AxiosInstance} from "axios";
import config from "@/config/config";

const apiClient : AxiosInstance = axios.create({
    baseURL: config.apiBaseUrl,
    headers: {
        'Content-Type': 'application/json',
    },
})

export default apiClient;
