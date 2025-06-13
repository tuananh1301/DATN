<template>
  <div aria-label="A complete example of page header">
    <el-page-header @back="onBack">
      <template #content>
        <div class="flex items-center">
          <el-avatar
            class="mr-3"
            :size="32"
            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
          <span class="text-large font-600 mr-3"> Title </span>
          <span
            class="text-sm mr-2"
            style="color: var(--el-text-color-regular)"
          >
            Sub title
          </span>
          <el-tag>Default</el-tag>
        </div>
      </template>
      <template #extra>
        <div class="flex items-center">
          <el-button>Print</el-button>
          <el-button type="primary" class="ml-2">Edit</el-button>
        </div>
      </template>
      <el-descriptions :column="3" size="small" class="mt-4"> </el-descriptions>
    </el-page-header>
  </div>
</template>
<style>
.el-page-header.is-contentful .el-page-header__main {
  border-top: 1px solid var(--el-border-color-light);
  margin-top: 8px;
}
</style>

<script setup lang="ts">
import { ElNotification as notify } from "element-plus";

const onBack = () => {
  notify("Back");
};
</script>
