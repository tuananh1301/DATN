<script setup>
import TableV1 from "./table/table.vue";
import SearchV1 from "./search/search1.vue";
import hoaDonButton from "./button/hoaDonButton.vue";
import checkOut from "./checkout/checkOut.vue";
</script>

<template>
  <div class="container">
    <el-row>
      <el-col :span="13">
        <div class="search1">
          <SearchV1></SearchV1>
        </div>
      </el-col>
      <el-col :span="11">
        <div class="hd">
          <hoaDonButton />
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="13">
        <div class="table1">
          <TableV1></TableV1>
        </div>
      </el-col>
      <el-col :span="11">
        <div class="checkout">
          <checkOut></checkOut>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
