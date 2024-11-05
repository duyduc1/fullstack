<template>
    <div class="login-container">
      <h2>Login</h2>
      <form @submit.prevent="handleLogin">
        <input
          v-model="email"
          type="email"
          placeholder="Email"
          required
        />
        <input
          v-model="password"
          type="password"
          placeholder="Password"
          required
        />
        <button type="submit">Log In</button>
      </form>
      <p v-if="errorMessage">{{ errorMessage }}</p>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from "vue";
  import { loginUser } from "@/services/loginService";
  import router from "@/router";

  export default defineComponent({
    setup() {
      const email = ref("");
      const password = ref("");
      const errorMessage = ref("");
  
      const handleLogin = async () => {
        try {
          const response = await loginUser({ email: email.value, password: password.value });
          if(response.token !== null){
            localStorage.setItem("token", response.token);
            localStorage.setItem("refreshToken", response.refreshToken);
            alert("Login successful!");
            router.push('/user');
          }
        } catch (error) {
          console.error("lỗi" , error)
        }
      };
  
      return { email, password, errorMessage, handleLogin };
    },
  });
  </script>
  
  <style scoped>
  .login-container {
    margin-top: 150px;
    max-width: 400px;
    margin: 200px auto 0;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.1);
  }
  input {
    width: 100%;
    margin-bottom: 15px;
    padding: 8px;
    
  }
  button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
  }
  p {
    color: red;
  }
  </style>
  