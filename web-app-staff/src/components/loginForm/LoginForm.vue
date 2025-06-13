<template>
  <div class="login-form">
    <h2 class="title">Sign in</h2>
    <el-form :model="form" ref="formRef" status-icon>
      <el-form-item>
        <el-input v-model="form.username" placeholder="Username" />
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="form.password"
          type="password"
          placeholder="Password"
          show-password
        />
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="form.remember">Remember me</el-checkbox>
        <a class="forgot" href="#">Forget password</a>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="w-full" @click="onSubmit"
          >Sign in</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button class="w-full">Register</el-button>
      </el-form-item>
      <div class="social-login">
        <span>Sign in with</span>
        <div class="icons">
          <i class="iconfont icon-github"></i>
          <i class="iconfont icon-wechat"></i>
          <i class="iconfont icon-alipay"></i>
          <i class="iconfont icon-weibo"></i>
        </div>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from "vue";
const form = ref({
  username: "",
  password: "",
  remember: true,
});

const onSubmit = () => {
  // Xử lý login tại đây
  alert("Đăng nhập!");
};
</script>

<style scoped>
.login-form {
  max-width: 380px;
  margin: 0 auto;
  padding: 40px 20px 0 20px;
}
.title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 24px;
  font-weight: 700;
}
.el-form-item {
  margin-bottom: 18px;
}
.w-full {
  width: 100%;
}
.forgot {
  float: right;
  color: #409eff;
  font-size: 13px;
}
.social-login {
  text-align: center;
  margin-top: 30px;
  color: #888;
  font-size: 14px;
}
.icons {
  margin-top: 16px;
  display: flex;
  justify-content: center;
  gap: 20px;
}
.iconfont {
  font-size: 26px;
  color: #ccc;
  cursor: pointer;
  transition: color 0.2s;
}
.iconfont:hover {
  color: #409eff;
}
</style>
