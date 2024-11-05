<template>
  <div class="register-form">
    <h2>Đăng ký tài khoản</h2>
    <form @submit.prevent="register"> <!-- Prevent default form submission -->
      <div class="form-group">
        <label for="email">Email:</label>
        <input v-model="newUser.email" type="email" id="email" required />
      </div>
      <div class="form-group">
        <label for="username">Tên đăng nhập:</label>
        <input v-model="newUser.username" type="text" id="username" required />
      </div>
      <div class="form-group">
        <label for="password">Mật khẩu:</label>
        <input v-model="newUser.password" type="password" id="password" required />
      </div>
      <div class="form-group">
        <label for="numberphone">Số điện thoại:</label>
        <input v-model="newUser.numberphone" type="number" id="numberphone" required />
      </div>
      <div class="form-group">
        <label for="company">Công ty:</label>
        <select v-model="newUser.companyId" id="company">
          <option value="">Chọn công ty (tùy chọn)</option> <!-- Optional selection -->
          <option v-for="company in companies" :key="company.id" :value="company.id">
            {{ company.companyname }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="gender">Giới tính:</label>
        <select v-model="newUser.genderId" id="gender">
          <option value="">Chọn giới tính (tùy chọn)</option> <!-- Optional selection -->
          <option v-for="gender in genders" :key="gender.id" :value="gender.id">
            {{ gender.genders }}
          </option>
        </select>
      </div>
      <button type="submit">Đăng ký</button>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed } from "vue";
import { registerUser } from "@/services/registerService";
import { registerStore } from "@/stores/registerStore";  
import router from "@/router";

export default defineComponent({
  setup() {
    const fetchGenders = registerStore();

    const newUser = computed(() => fetchGenders.user);
    const genders = computed(() => fetchGenders.genders);
    const companies = computed(() => fetchGenders.companies);
    

    const register = async () => {
      try {
        const response = await registerUser(newUser.value);
        
        if (response.statusCode === 200) {
          router.push('/login');
        }
      } catch (error) {
        console.error("Registration error:", error);
      }
    };

    fetchGenders.fetchCompaniesAndGenders();

    return { newUser, genders, companies, register };
  },
});
</script>

<style scoped>
.register-form {
  max-width: 400px;
  margin: 200px auto 0;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="email"],
input[type="text"],
input[type="password"],
input[type="number"],
select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #28a745;
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #218838;
}
</style>
