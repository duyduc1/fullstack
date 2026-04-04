<!-- <template>
  <header>
    <div class="logo-furniture">
      <router-link class="navbar-brand" to="/"
        >DUcK <span class="tooplate-red">Furni</span
        ><span class="tooplate-green">ture</span></router-link
      >
    </div>
    <button class="hamburger" @click="toggleMenu">☰</button>
    <div class="nav-bar" :class="{ 'show-menu': isMenuOpen }">
      <router-link class="home-link" to="/">Home</router-link>
      <router-link to="/about">About</router-link>
      <router-link to="/products">Shop</router-link>
      <router-link to="/login">Login</router-link>
      <router-link to="/register">Register</router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: "HeaderComponent",
  data() {
    return {
      isMenuOpen: false,
    };
  },
  methods: {
    toggleMenu() {
      this.isMenuOpen = !this.isMenuOpen;
    },
  },
};
</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f8f9fa;
  padding: 20px 120px;
  color: white;
  height: 75px;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

.hamburger {
  display: none;
  font-size: 24px;
  background: none;
  border: none;
  cursor: pointer;
  position: relative; 
}

.home-link {
  background-color: #ffd700 !important; 
  color: #fff !important; 
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  height: 100%; 
  transition: all 0.3s ease; 
}


.home-link:hover {
  background-color: #ffc107 !important; 
}

.navbar-brand {
  font-size: 33px;
  font-weight: 800;
  color: #fdda4f;
}

.tooplate-red {
  color: #f65129;
}

.tooplate-green {
  color: #48a45d;
}

a {
  text-decoration: none;
}

.nav-bar {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 45%;
  font-size: 18px;
}

.nav-bar.show-menu {
  display: flex;
  flex-direction: column; 
  position: absolute;
  top: 75px;
  right: 0;
  background: #fff;
  width: 100%;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
  z-index: 1000;
}

.nav-bar a {
  padding: 29px 35px;
  background-color: transparent;
  color: black;
  font-weight: normal;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  transition: all 0.3s ease;
}

.nav-bar a:hover {
  padding: 28px 34px;
  background-color: #ffd700;
  color: white;
  transform: scale(1.05);
  font-weight: bold;
}

@media screen and (max-width: 768px) {
  
  .navbar-brand {
    font-size: 25px;
    margin-left: -60px;
  }
  .hamburger {
    display: block; 
  }
  .nav-bar {
    display: none; 
  }
  .nav-bar.show-menu {
    display: flex; 
  }
  .nav-bar a {
    padding: 29px 380px;
    background-color: transparent;
    color: black;
    font-weight: normal;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    transition: all 0.3s ease;
  }
  .nav-bar a:hover {
    padding: 28px 34px;
    background-color: #ffd700;
    color: white;
    transform: scale(1.05);
    font-weight: bold;
  }
}
</style> -->