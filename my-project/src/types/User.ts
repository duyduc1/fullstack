export interface User {
    id?: number;
    email: string;
    username: string;
    numberphone: number | null;
    password : string;
    role:string;
    companyId: number | null;
    genderId: number | null;
}
