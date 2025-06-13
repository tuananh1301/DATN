<template>
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-row>
      <el-form-item label="Mã sản phẩm: ">
        <el-input
          v-model="formInline.user"
          placeholder="Nhập mã sản phẩm"
          clearable
        />
      </el-form-item>
      <el-form-item label="Màu sắc: ">
        <el-select
          v-model="formInline.region"
          placeholder="Chọn màu sắc"
          clearable
        >
          <el-option label="Zone one" value="shanghai" />
          <el-option label="Zone two" value="beijing" />
        </el-select>
      </el-form-item>
    </el-row>
    <el-row>
      <el-form-item label="Kích thước: ">
        <el-select
          class="selectA"
          v-model="formInline.size"
          placeholder="Chọn kích thước"
          clearable
        >
          <el-option label="Size S" value="S" />
          <el-option label="Size M" value="M" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Query</el-button>
      </el-form-item>
    </el-row>
  </el-form>
</template>

<script lang="ts" setup>
import { reactive } from "vue";

const formInline = reactive({
  user: "",
  region: "",
  date: "",
});

const onSubmit = () => {
  console.log("submit!");
};
</script>

<style>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
.selectA {
  padding-left: 12px;
}
</style>
