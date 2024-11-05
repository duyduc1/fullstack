export interface User {
    id?: number;
    email: string;
    username: string;
    numberphone: number | null;
    password : string;
    role:string;
    companyId: number | null;
    genderId: number | null;
    statusCode?: number; 
}

export interface LoginRequest {
    email: string;
    password: string;
    username?: string;
  }

export interface LoginResponse {
    token: string;
    refreshToken: string;
  }