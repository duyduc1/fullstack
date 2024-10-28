
<template>
    <div>
      <Header />
      <div class="p-0">
        <router-view></router-view>
      </div>
      <Footer />
    </div>
  </template>
  
  <script>
  import Header from "@/components/common/Header.vue";
  import Footer from "@/components/common/Footer.vue";
  
  export default {
    name: 'AppLayout',
    components: {
      Header,
      Footer
    }
  };
  </script>
  
  <style scoped>
  main {
    padding: 20px;
  }
  </style>
  