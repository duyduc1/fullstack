import {defineStore} from "pinia";
import {companyService} from "@/services/companyService";
import {Company} from "@/types/Company";

export const useCompanyStore = defineStore('companyStore', {
    state:() => ({
        companies: [] as Company[],
        newCompany: {
            companyname: '',
            users: [],
        } as Company,
        isEditing: false,
        editCompanyId: null as number | null,
    }),
    actions :{
        async fetchCompany() {
            try {
                this.companies = await companyService.getAllCompany();
            }catch (error) {
                console.error("Error fetching companies" , error);
            }
        },

        async addCompany() {
            try {
                const savedCompany = await companyService.saveCompany(this.newCompany);
                this.companies.push(savedCompany);
                this.resetForm()
            }catch (error) {
                console.error("Error adding company" , error)
            }
        },

        setEditCompany(company : Company){
            this.newCompany = {...company};
            this.editCompanyId = company.id ?? null;
            this.isEditing = true;
        },

        async updateCompany() {
            if (this.editCompanyId){
                try {
                    await companyService.updateCompany(this.editCompanyId , this.newCompany);
                    const index = this.companies.findIndex(c => c.id === this.editCompanyId);
                    if (index !== 1) {
                        this.companies[index] = {...this.newCompany , id: this.editCompanyId};
                    }
                }catch (error) {
                    console.error("Errir updating company" , error);
                }
            }
        },

        async deleteCompany(id:number) {
            try{
                await companyService.deleteCompany(id);
                this.companies = this.companies.filter(c => c.id !== id);
            }catch (error) {
                console.error("Error deleting company" , error)
            }
        },

        resetForm() {
            this.newCompany = {companyname: '' , users: []};
            this.isEditing = false;
            this.editCompanyId = null;
        }
    }
})