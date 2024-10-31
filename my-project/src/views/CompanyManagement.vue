<template>
  <div class="company_container">
    <h1>Company Management</h1>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Công ty</th>
          <th>Người dùng</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="company in companyStore.companies" :key="company.id">
          <td>{{ company.id }}</td>
          <td>{{ company.companyname }}</td>
          <td>
            <ul>
              <li v-for="user in company.users" :key="user.id">
                ID Người Dùng : {{user.id}} <br>
                Tên Người Dùng : {{ user.username }} <br>
                Email : {{ user.email }} <br>
                Số Điện Thoại {{ user.numberphone }} <hr></li>
            </ul>
          </td>
          <td>
            <button @click="companyStore.setEditCompany(company)">Sửa</button>
            <button @click="companyStore.deleteCompany(company.id)">Xoá</button>
          </td>
        </tr>
      </tbody>
    </table>
    <form @submit.prevent="companyStore.isEditing ? companyStore.updateCompany() : companyStore.addCompany()">
      <input v-model="companyStore.newCompany.companyname" placeholder="Nhập công ty" required>
      <button type="submit">{{ companyStore.isEditing ? 'Cập nhật công ty' : 'Thêm công ty' }}</button>
    </form>
  </div>
</template>

<script lang="ts">
import {defineComponent,onMounted} from "vue";
import {useCompanyStore} from "@/stores/companyStore";

export default defineComponent({
  setup() {
    const companyStore = useCompanyStore();

    onMounted(() => {
      companyStore.fetchCompany();
    });

    return {
      companyStore,
    };
  },
});

</script>

<style>
.company_container {
  margin-top: 150px;
}

table {
  width: 100%;
  border-collapse: collapse;
  text-align: center;
}

th,
td {
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
