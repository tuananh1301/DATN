<template>
  <div class="login-container">
    <div class="login-left">
      <div class="logo">
        <img src="../assets/vue.svg" width="40" />
        <span>ElementAdmin</span>
      </div>
      <img class="illustration" src="../assets/rm314-adj-10.jpg" />
      <h2>Welcome to the system</h2>
      <div class="desc">Backstage management system</div>
    </div>
    <div class="login-right">
      <LoginForm />
    </div>
  </div>
</template>

<script setup>
import LoginForm from "../components/loginForm/LoginForm.vue";
</script>

<style scoped>
.login-container {
  display: flex;
  height: 100vh;
}
.login-left {
  flex: 1;
  background: #393e54;
  color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-width: 420px;
}
.logo {
  display: flex;
  align-items: center;
  font-size: 1.5rem;
  font-weight: 700;
  margin-bottom: 40px;
  gap: 8px;
}
.illustration {
  max-width: 340px;
  margin-bottom: 36px;
}
.login-right {
  flex: 1;
  background: #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
h2 {
  font-size: 2rem;
  margin-bottom: 18px;
}
.desc {
  font-size: 1.1rem;
  color: #ccc;
}
</style>
