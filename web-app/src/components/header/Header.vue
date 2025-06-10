<template>
  <el-header class="el-header-custom">
    <el-row align="middle" justify="space-between">
      <!-- Logo -->
      <el-col :span="4">

      </el-col>

      <el-col :span="8">
        <el-input
          v-model="search"
          placeholder="Search…"
          clearable
          prefix-icon="el-icon-search"
        ></el-input>
      </el-col>

      <el-col :span="12">
        <div class="el-header-icons">
          <el-badge :value="4" class="icon-btn">
            <el-button type="text" icon="el-icon-message"></el-button>
          </el-badge>
          <el-badge :value="17" class="icon-btn">
            <el-button type="text" icon="el-icon-bell"></el-button>
          </el-badge>
          <el-dropdown @command="handleMenuCommand">
            <span class="el-dropdown-link">
              <el-avatar icon="el-icon-user-solid" size="medium" />
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">Profile</el-dropdown-item>
                <el-dropdown-item command="settings">Settings</el-dropdown-item>
                <el-dropdown-item command="logout">Log Out</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </el-header>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { logOut } from '@/services/authenticationService'

const search = ref('')

function handleMenuCommand(command) {
  if (command === 'logout') {
    logOut()
    window.location.href = '/login'
  } else {
    ElMessage.info(`Chức năng "${command}" chưa được cài đặt!`)
  }
}
</script>

<style scoped>
.el-header-custom {
  background: #409eff;
  color: #fff;
}
.el-header-icons {
  display: flex;
  align-items: center;
  gap: 20px;
  justify-content: flex-end;
}
.icon-btn {
  margin-right: 10px;
}
.el-dropdown-link {
  cursor: pointer;
}
</style>
