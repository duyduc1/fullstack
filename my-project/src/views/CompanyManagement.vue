<template>
  <div>
    <h1>Company Management</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Công ty</th>
        <th>Hành động</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="company in companies" :key="company.id">
        <td>{{company.id}}</td>
        <td>{{company.companyname}}</td>
        <td>
          <button @click="setEditCompanies(company)">Sửa</button>
          <button @click="deleteCompany(company.id!)">Xoá</button>
        </td>
      </tr>
      </tbody>
    </table>
    <form @submit.prevent="isEditing ? updateCompany() : addCompany()">
      <input v-model="newCompany.companyname" placeholder="Nhập công ty" required>
      <button type="submit">{{isEditing ? 'Cập nhật công ty' : 'Thêm công ty'}}</button>
    </form>
  </div>
</template>

<script lang="ts">
import {defineComponent , ref , onMounted} from "vue";
import {companyService , Company} from "@/services/companyService";

export default defineComponent({
  setup() {
    const companies = ref<Company[]>([]);
    const newCompany = ref<Company>({ companyname : ''});
    const isEditing = ref(false);
    const editCompanyId = ref<number | null>(null);

    const fetchCompany = async () => {
      try {
        companies.value = await companyService.getAllCompany();
      }catch (error) {
        console.error("Error fetching company:" , error);
      }
    };

    const addCompany = async () => {
      try{
        const saveCompanies = await companyService.saveCompany(newCompany.value);
        companies.value.push(saveCompanies)
        newCompany.value.companyname = '';
      }catch (error){
        console.error('Error fetching genders:', error);
      }
    };

    const setEditCompanies = (company : Company) => {
      newCompany.value = { ...company };
      editCompanyId.value = company.id !== undefined ? company.id : null;
      isEditing.value = true;
    }

    const updateCompany = async () => {
      if (editCompanyId.value) {
        try {
          await companyService.updateCompany(editCompanyId.value , newCompany.value);
          const index = companies.value.findIndex(company => company.id === editCompanyId.value);
          if (index !== -1){
            companies.value[index] = { ...newCompany.value, id:editCompanyId.value};
          }
          resetForm();
        }catch (error) {
          console.error('Error updating gender:' , error)
        }
      }
    };

    const deleteCompany = async (id: number) => {
      try {
        console.log(`Deleting company with id: ${id}`);
        await companyService.deleteCompany(id);
        companies.value = companies.value.filter(company => company.id !== id);
      } catch (error) {
        console.error("Error deleting company:", error);
      }
    };


    const resetForm = () => {
      newCompany.value = {companyname: ''};
      isEditing.value = false;
      editCompanyId.value = null;
    };

    onMounted(fetchCompany);

    return {
      companies,
      newCompany,
      isEditing,
      addCompany,
      updateCompany,
      deleteCompany,
      setEditCompanies,
      resetForm,
    }
  }
})
</script>

<style>

table {
  width: 100%;
  border-collapse: collapse;
  text-align: center;
}

th, td {
  padding: 8px 12px;
  border: 1px solid #ddd;
  text-align: left;
}

th {
  background-color: #f4f4f4;
}

button {
  margin-right: 5px;
}
</style>