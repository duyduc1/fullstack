import { defineStore } from "pinia";
import { genderService } from "@/services/genderService";
import { companyService } from "@/services/companyService";
import { User } from '@/types/User';
import { Company } from "@/types/Company";
import { Gender } from "@/types/Gender";
import {registerUser} from "@/services/registerService";

export const registerStore = defineStore("user", {
    state: () => ({
        users: [] as User[],
        user: {
            email: '',
            username: '',
            password: '',
            role: 'USER',
            numberphone: null,
            companyId: null,
            genderId: null,
        } as User,
        genders: [] as Gender[],
        companies: [] as Company[],
    }),
    actions: {
        async fetchCompaniesAndGenders() {
            try {
                this.companies = await companyService.getAllCompany();
                this.genders = await genderService.getAllGenders();
            } catch (error) {
                console.error("Lỗi khi lấy danh sách công ty hoặc giới tính", error);
            }
        },

        async signUp() {
            try {
               const registeUser = await registerUser(this.user)
                this.users.push(registeUser);
            } catch (error) {
                console.error("Error adding user:", error);
            }
        }
    }
});
