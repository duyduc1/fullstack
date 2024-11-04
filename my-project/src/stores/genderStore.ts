import { defineStore } from 'pinia';
import { genderService } from '@/services/genderService';
import { Gender } from "@/types/Gender";

export const useGenderStore = defineStore('genderStore', {
    state: () => ({
        genders: [] as Gender[],
        newGender: {
            genders: '',
            users: [],
        } as Gender,
        isEditing: false,
        editGenderId: null as number | null,
    }),
    actions: {
        async fetchGenders() {
            try {
                this.genders = await genderService.getAllGenders();
            } catch (error) {
                console.error("Error fetching genders:", error);
            }
        },
        async addGender() {
            try {
                const savedGender = await genderService.saveGender(this.newGender);

                this.genders.push(savedGender);
                this.resetForm();
            } catch (error) {
                console.error("Error adding gender:", error);
            }
        },
        setEditGender(gender: Gender) {
            this.newGender = { ...gender };
            this.editGenderId = gender.id ?? null;
            this.isEditing = true;
        },
        async updateGender() {
            if (this.editGenderId) {
                try {
                    await genderService.updateGender(this.editGenderId, this.newGender);
                    const index = this.genders.findIndex(g => g.id === this.editGenderId);
                    if (index !== -1) {
                        this.genders[index] = { ...this.newGender, id: this.editGenderId };
                    }
                    this.resetForm();
                } catch (error) {
                    console.error("Error updating gender:", error);
                }
            }
        },
        async deleteGender(id: number) {
            try {
                await genderService.deleteGender(id);
                this.genders = this.genders.filter(g => g.id !== id);
            } catch (error) {
                console.error("Error deleting gender:", error);
            }
        },
        resetForm() {
            this.newGender = { genders: '', users: [] };
            this.isEditing = false;
            this.editGenderId = null;
        },
    },
});
