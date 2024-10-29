<template>
  <div class="gender_container">
    <h1>Gender Management</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Giới tính</th>
        <th>Hành động</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="gender in genders" :key="gender.id">
        <td>{{ gender.id }}</td>
        <td>{{ gender.genders }}</td>
        <td>
          <button @click="setEditGender(gender)">sửa</button>
          <button @click="deleteGender(gender.id!)">xoá</button>
        </td>
      </tr>
      </tbody>
    </table>
    <form @submit.prevent="isEditing ? updateGender() : addGender()">
      <input v-model="newGender.genders" placeholder="Nhập giới tinh" required />
      <button type="submit">{{ isEditing ? 'Cập nhật giới tính' : 'Thêm giới tính' }}</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import { genderService } from '@/services/genderService';
import {Gender} from "@/types/Gender";

export default defineComponent({
  setup() {
    const genders = ref<Gender[]>([]);
    const newGender = ref<Gender>({ genders: '' });
    const isEditing = ref(false);
    const editGenderId = ref<number | null>(null);

    const fetchGenders = async () => {
      try {
        genders.value = await genderService.getAllGenders();
      } catch (error) {
        console.error('Error fetching genders:', error);
      }
    };

    const addGender = async () => {
      try {
        const savedGender = await genderService.saveGender(newGender.value);
        genders.value.push(savedGender);
        newGender.value.genders = '';
      } catch (error) {
        console.error('Error adding gender:', error);
      }
    };

    const setEditGender = (gender: Gender) => {
      newGender.value = { ...gender };
      editGenderId.value = gender.id !== undefined ? gender.id : null;
      isEditing.value = true;
    };

    const updateGender = async () => {
      if (editGenderId.value) {
        try {
          await genderService.updateGender(editGenderId.value, newGender.value);
          const index = genders.value.findIndex(gender => gender.id === editGenderId.value);
          if (index !== -1) {
            genders.value[index] = { ...newGender.value, id: editGenderId.value };
          }
          resetForm();
        } catch (error) {
          console.error('Error updating gender:', error);
        }
      }
    };

    const deleteGender = async (id: number) => {
      try {
        await genderService.deleteGender(id);
        genders.value = genders.value.filter(gender => gender.id !== id);
      } catch (error) {
        console.error('Error deleting gender:', error);
      }
    };

    const resetForm = () => {
      newGender.value = { genders: '' };
      isEditing.value = false;
      editGenderId.value = null;
    };

    onMounted(fetchGenders);

    return {
      genders,
      newGender,
      isEditing,
      addGender,
      updateGender,
      deleteGender,
      setEditGender,
      resetForm,
    };
  },
});
</script>

<style scoped>

.gender_container {
  margin-top: 200px;
}
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
