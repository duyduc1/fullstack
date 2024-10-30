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
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.id">
        <td>{{ user.id }}</td>
        <td>{{ user.email }}</td>
        <td>{{ user.username }}</td>
        <td>{{ user.numberphone }}</td>
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
import { defineComponent, ref, onMounted } from "vue";
import { userService } from "@/services/userService";
import { genderService } from "@/services/genderService";
import { companyService } from "@/services/companyService";
import { User } from "@/types/User";
import { Gender } from "@/types/Gender";
import { Company } from "@/types/Company";

export default defineComponent({
  setup() {
    const users = ref<User[]>([]);
    const newUser = ref<User>({
      email: '',
      username: '',
      numberphone: 0,
      companyId: 0,
      genderId: 0,
    });
    const isEditing = ref(false);
    const editUserId = ref<number | null>(null);
    const genders = ref<Gender[]>([]);
    const companies = ref<Company[]>([]);

    const fetchUsers = async () => {
      try {
        users.value = await userService.getAllUsers();
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };

    const fetchGenders = async () => {
      try {
        genders.value = await genderService.getAllGenders();
      } catch (error) {
        console.error("Error fetching genders:", error);
      }
    };

    const fetchCompanies = async () => {
      try {
        companies.value = await companyService.getAllCompany();
      } catch (error) {
        console.error("Error fetching companies:", error);
      }
    };

    const addUser = async () => {
      try {
        const savedUser = await userService.saveUser(newUser.value);
        users.value.push(savedUser);
        resetForm();
      } catch (error) {
        console.error("Error adding user:", error);
      }
    };

    const setEditUser = (user: User) => {
      newUser.value = { ...user };
      editUserId.value = user.id !== undefined ? user.id : null;
      isEditing.value = true;
    };

    const updateUser = async () => {
      if (editUserId.value) {
        try {
          await userService.updateUser(editUserId.value, newUser.value);
          const index = users.value.findIndex(user => user.id === editUserId.value);
          if (index !== -1) {
            users.value[index] = { ...newUser.value, id: editUserId.value };
          }
          resetForm();
        } catch (error) {
          console.error("Error updating user:", error);
        }
      }
    };

    const deleteUser = async (id: number) => {
      try {
        await userService.deleteUser(id);
        users.value = users.value.filter(user => user.id !== id);
      } catch (error) {
        console.error("Error deleting user:", error);
      }
    };

    const resetForm = () => {
      newUser.value = { email: '', username: '', numberphone: 0, companyId: 0, genderId: 0 };
      isEditing.value = false;
      editUserId.value = null;
    };

    onMounted(() => {
      fetchUsers();
      fetchGenders();
      fetchCompanies();
    });

    return {
      users,
      newUser,
      isEditing,
      addUser,
      updateUser,
      deleteUser,
      setEditUser,
      resetForm,
      genders,
      companies, // Return the genders and companies for the template
    };
  },
});
</script>

<style>
.user-container {
  margin-top: 200px;
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
