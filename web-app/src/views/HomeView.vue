<template>
  <div class="home">
    <DiaChiComponent msg="Welcome to Your Vue.js App" />
  </div>
  <h1>Home Page Test</h1>
</template>

<script>
import DiaChiComponent from "../components/DiaChiComponent.vue";

export default {
  name: "HomeView",
  components: {
    DiaChiComponent,
  },
};
</script>
