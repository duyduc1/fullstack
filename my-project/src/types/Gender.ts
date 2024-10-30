import { User } from "./User";

export interface Gender {
    id?: number ;
    genders: string;
    users : User[];
}