<template>
  <div class="register-form">
    <h2>Đăng ký tài khoản</h2>
    <form>
      <div>
        <label for="email">Email:</label>
        <input v-model="newUser.email" type="email" id="email" required />
      </div>
      <div>
        <label for="username">Tên đăng nhập:</label>
        <input v-model="newUser.username" type="text" id="username" required />
      </div>
      <div>
        <label for="password">Mật khẩu:</label>
        <input v-model="newUser.password" type="password" id="password" required />
      </div>
      <div>
        <label for="numberphone">Số điện thoại:</label>
        <input v-model="newUser.numberphone" type="number" id="numberphone" required />
      </div>
      <div>
        <label for="company">Công ty:</label>
        <select v-model="newUser.companyId" id="company" required >
          <option v-for="company in companies" :key="company.id" :value="company.id" >
            {{ company.companyname }}
          </option>
        </select>
      </div>
      <div>
        <label for="gender">Giới tính:</label>
        <select v-model="newUser.genderId" id="gender" required>
          <option v-for="gender in genders" :key="gender.id" :value="gender.id">
            {{ gender.genders }}
          </option>
        </select>
      </div>
      <button @click="register" type="submit">Đăng ký</button>
    </form>
  </div>
</template>

<script lang="ts">
import {defineComponent  ,computed} from "vue";
import {registerUser} from "@/services/registerService";
import {registerStore} from "@/stores/registerStore";

export default defineComponent({
  setup(){
    const fetchGenders = registerStore();

    const newUser = computed(() => fetchGenders.user);
    const genders = computed(() => fetchGenders.genders);
    const companies = computed(() => fetchGenders.companies);

    const register = async () => {
      await registerUser(newUser.value)
    }

    fetchGenders.fetchCompaniesAndGenders();

    return { newUser , genders , companies , register}
  }
})

</script>