import { User } from "./User";

export interface Company {
    id?: number ;
    companyname: string;
    users : User[];
}
