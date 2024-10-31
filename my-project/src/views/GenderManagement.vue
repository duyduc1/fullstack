<template>
  <div class="gender_container">
    <h1>Gender Management</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Giới tính</th>
        <th>Người dùng</th>
        <th>Hành động</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="gender in genderStore.genders" :key="gender.id">
        <td>{{ gender.id }}</td>
        <td>{{ gender.genders }}</td>
        <td>
          <ul>
            <li v-for="user in gender.users" :key="user.id">
              ID Người Dùng : {{user.id}} <br>
              Tên Người Dùng : {{ user.username }} <br>
              Email : {{ user.email }} <br>
              Số Điện Thoại : {{ user.numberphone }} <hr>
            </li>
          </ul>
        </td>
        <td>
          <button @click="genderStore.setEditGender(gender)">sửa</button>
          <button @click="genderStore.deleteGender(gender.id)">xoá</button>
        </td>
      </tr>
      </tbody>
    </table>
    <form @submit.prevent="genderStore.isEditing ? genderStore.updateGender() : genderStore.addGender()">
      <input v-model="genderStore.newGender.genders" placeholder="Nhập giới tính" required />
      <button type="submit">{{ genderStore.isEditing ? 'Cập nhật giới tính' : 'Thêm giới tính' }}</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from 'vue';
import { useGenderStore } from '@/stores/genderStore';

export default defineComponent({
  setup() {
    const genderStore = useGenderStore();

    onMounted(() => {
      genderStore.fetchGenders();
    });

    return {
      genderStore,
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
