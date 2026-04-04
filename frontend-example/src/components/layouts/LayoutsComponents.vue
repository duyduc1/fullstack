<template>
  <div>
    <HeaderComponent />
    <main>
      <router-view />
    </main>
    <FooterComponent />
  </div>
</template>

<script>
// import HeaderComponent from "../common/HeaderComponent.vue";
// import FooterComponent from "../common/FooterComponent.vue";
export default {
  name: "LayoutsComponents",
  components: {
    // HeaderComponent,
    // FooterComponent
  },
};
</script>