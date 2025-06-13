<script setup>
import Sidebar from "../components/sidebar/Sidebar.vue";
import HeaderApp from "../components/header/HeaderApp.vue";
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <Sidebar />
      </el-aside>
      <el-container>
        <el-header heigh="90px">
          <HeaderApp></HeaderApp>
        </el-header>
        <el-main>
          <slot />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.el-page-header__header {
  align-items: flex-start;
}
</style>
