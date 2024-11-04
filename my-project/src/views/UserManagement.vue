<template>
  <div class="user-container">
    <h1>User Management</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Username</th>
        <th>Number Phone</th>
        <th>Role</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id">
        <td>{{ user.id }}</td>
        <td>{{ user.email }}</td>
        <td>{{ user.username }}</td>
        <td>{{ user.numberphone }}</td>
        <td>{{user.role}}</td>
        <td>
          <button @click="setEditUser(user)">Edit</button>
          <button @click="deleteUser(user.id)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <form @submit.prevent="isEditing ? updateUser() : addUser()">
      <input v-model="newUser.email" placeholder="Enter email" required />
      <input v-model="newUser.username" placeholder="Enter username" required />
      <input v-model="newUser.numberphone" type="number" placeholder="Enter number phone" required />
      <select v-model="newUser.genderId" required>
        <option v-for="gender in genders" :key="gender.id" :value="gender.id">{{ gender.genders }}</option>
      </select>
      <select v-model="newUser.companyId" required>
        <option v-for="company in companies" :key="company.id" :value="company.id">{{ company.companyname }}</option>
      </select>
      <button type="submit">{{ isEditing ? 'Update User' : 'Add User' }}</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed } from "vue";
import {useUserStore} from "@/stores/userStore";
import {User} from "@/types/User";

export default defineComponent({
  setup() {
    const userStore = useUserStore();

    const users = computed(() => userStore.users);
    const newUser = computed(() => userStore.newUser);
    const isEditing = computed(() => userStore.isEditing);
    const genders = computed(() => userStore.genders);
    const companies = computed(() => userStore.companies);

    const addUser = () => userStore.addUser();
    const updateUser = () => userStore.updateUser();
    const deleteUser = (id: number) => userStore.deleteUser(id);
    const setEditUser = (user: User) => userStore.setEditUser(user);

    userStore.fetchUsers();
    userStore.fetchGenders();
    userStore.fetchCompanies();

    return { users, newUser, isEditing, addUser, updateUser, deleteUser, setEditUser, genders, companies };
  },
});
</script>

<style>
.user-container {
  margin-top: 150px;
}
table {
  width: 100%;
  border-collapse: collapse;
  text-align: left;
}
th, td {
  padding: 8px 12px;
  border: 1px solid #ddd;
}
th {
  background-color: #f4f4f4;
}
</style>
