<template>
    <header>
      <div class="logo">
        <img src="@/assets/images/logo.png" alt="Logo">
        <span>MY PORTAL</span>
      </div>
  
      <div class="hamburger-menu" @click="toggleMenu">
        <span></span>
        <span></span>
        <span></span>
      </div>
  
      <nav :class="{ 'show-menu': menuOpen }">
        <ul class="nav-links">
          <li><router-link to="/">Trang chủ</router-link></li>
          <li><router-link to="/company">Tiện ích từ chính quyền</router-link></li>
          <li><router-link to="/gender">Dịch vụ</router-link></li>
          <li><router-link to="/user">Hỗ trợ</router-link></li>
        </ul>
      </nav>
  
      <div :class="{ 'auth-links': true, 'show-menu': menuOpen }">
        <a href="#" class="login">Đăng nhập</a>
        <a href="#" class="signup">Đăng ký</a>
      </div>
    </header>
  </template>
  
  <script>
  export default {
    name: 'HeaderComponent',
    data() {
      return {
        menuOpen: false,
      };
    },
    methods: {
      toggleMenu() {
        this.menuOpen = !this.menuOpen;
      },
    },
  };
  </script>
  
  <style scoped>
  header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #0557A5;
    padding: 10px 20px;
    color: white;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 1000;
  }
  
  .logo {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .logo span {
    font-weight: bold;
    font-size: 20px;
    margin-left: 10px;
    font-family: 'M PLUS Rounded 1c', sans-serif;
  }
  
  .logo img {
    height: 50px;
    margin-left: 50px;
  }
  
  nav {
    flex-grow: 1;
    display: flex;
    justify-content: center;
    transition: transform 0.3s ease;
  }
  
  .nav-links {
    list-style: none;
    display: flex;
    gap: 20px;
    padding-left: 540px;
  }
  
  .nav-links li a {
    font-weight: 700;
    font-size: 16px;
    line-height: 18.75px;
    align-items: center;
    color: white;
    text-decoration: none;
  }
  
  .auth-links {
    display: flex;
    gap: 10px;
    justify-content: center;
    align-items: center;
    margin-right: 10px;
  }
  
  .auth-links a {
    text-decoration: none;
    color: white;
    font-size: 16px;
  }
  
  .nav-links li a:hover {
    border: 3px solid #fff;
    border-right: none;
    border-top: none;
    border-left: none;
  }
  
  .login {
    font-weight: bold;
  }
  
  .signup {
    padding: 5px 25px;
    background-color: transparent;
    border: 1px solid #fff;
    border-radius: 5px;
    font-weight: bold;
  }
  
  .hamburger-menu {
    display: none;
    flex-direction: column;
    cursor: pointer;
    gap: 5px;
    margin-right: 2rem;
  }
  
  .hamburger-menu span {
    width: 25px;
    height: 3px;
    background-color: white;
  }
  
  @media (max-width: 768px) {
    nav, .auth-links {
      display: none;
      position: absolute;
      top: 60px;
      left: 0;
      width: 100%;
      flex-direction: column;
      background-color: #0056A3;
      text-align: center;
    }
  
  
    .nav-links, .auth-links {
      flex-direction: column;
      gap: 10px;
      padding: 0;
  
    }
  
    .nav-links li {
      width: 100%;
    }
  
    .nav-links li, .auth-links a {
      padding: 10px 30px;
    }
  
    .hamburger-menu {
      display: flex;
    }
  
    .show-menu {
      display: flex;
    }
  
    .login{
      font-weight: bold;
    }
  
    .signup{
      border: 1px solid transparent;
      font-weight: bold;
    }
  
    .nav-links{
      margin-top: 105px;
      height: 14rem;
    }
  
    .show-menu nav,
    .show-menu .auth-links {
      display: flex;
    }
  }
  
  @media (max-width: 480px) {
    .logo img {
      height: 40px;
    }
  
    .hamburger-menu span {
      width: 20px;
    }
  }
  </style>
  