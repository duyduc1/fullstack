import { defineStore } from 'pinia';
import {userService} from "@/services/userService";
import {genderService} from "@/services/genderService";
import {companyService} from "@/services/companyService";
import { User } from '@/types/User';
import {Company} from "@/types/Company";
import {Gender} from "@/types/Gender";

export const useUserStore = defineStore('userStore', {
    state: () => ({
        users: [] as User[],
        newUser: {
            email: '',
            username: '',
            numberphone: 0,
            companyId: 0,
            genderId: 0,
        } as User,
        isEditing: false,
        editUserId: null as number | null,
        genders: [] as Gender[],
        companies: [] as Company[]
    }),
    actions: {
        async fetchUsers() {
            try {
                this.users = await userService.getAllUsers();
            } catch (error) {
                console.error("Error fetching users:", error);
            }
        },

        async fetchGenders() {
            try {
                this.genders = await genderService.getAllGenders();
            } catch (error) {
                console.error("Error fetching genders:", error);
            }
        },

        async fetchCompanies() {
            try {
                this.companies = await companyService.getAllCompany();
            } catch (error) {
                console.error("Error fetching companies:", error);
            }
        },

        async addUser() {
            try {
                const savedUser = await userService.saveUser(this.newUser);
                this.users.push(savedUser);
                this.resetForm();
            } catch (error) {
                console.error("Error adding user:", error);
            }
        },
        setEditUser(user: User) {
            this.newUser = { ...user };
            this.editUserId = user.id ?? null;
            this.isEditing = true;
        },
        async updateUser() {
            if (this.editUserId) {
                try {
                    await userService.updateUser(this.editUserId, this.newUser);
                    const index = this.users.findIndex((user) => user.id === this.editUserId);
                    if (index !== -1) {
                        this.users[index] = { ...this.newUser, id: this.editUserId };
                    }
                    this.resetForm();
                } catch (error) {
                    console.error("Error updating user:", error);
                }
            }
        },
        async deleteUser(userId: number) {
            try {
                await userService.deleteUser(userId);
                this.users = this.users.filter((user) => user.id !== userId);
            } catch (error) {
                console.error("Error deleting user:", error);
            }
        },
        resetForm() {
            this.newUser = {
                email: '',
                username: '',
                numberphone: 0,
                companyId: 0,
                genderId: 0,
            };
            this.isEditing = false;
            this.editUserId = null;
        },
    },
});
