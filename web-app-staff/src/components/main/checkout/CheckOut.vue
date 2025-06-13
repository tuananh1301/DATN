<template>
  <div class="pos-checkout">
    <div class="pos-header">
      <el-row align="middle" justify="space-between">
        <el-col :span="14">
          <el-input
            v-model="customer"
            placeholder="Tìm khách hàng (F4)"
            prefix-icon="el-icon-search"
            size="large"
            class="input-search"
          />
        </el-col>
        <el-col :span="4">
          <el-select
            v-model="priceList"
            placeholder="Bảng giá chung"
            size="large"
          >
            <el-option label="Bảng giá chung" value="default" />
            <el-option label="Bảng giá VIP" value="vip" />
          </el-select>
        </el-col>
        <el-col :span="6" class="datetime">
          <span>{{ storeName }}</span>
          <span>{{ now }}</span>
        </el-col>
      </el-row>
    </div>

    <div class="pos-summary">
      <el-row>
        <el-col :span="14" class="label">Tổng tiền hàng</el-col>
        <el-col :span="10" class="value bold right">{{
          total.toLocaleString()
        }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="14" class="label">Giảm giá</el-col>
        <el-col :span="10" class="value right">
          <el-input v-model="discount" size="small" placeholder="0" />
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="14" class="label">Thu khác</el-col>
        <el-col :span="10" class="value right">
          <el-input v-model="extra" size="small" placeholder="0" />
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="14" class="label bold highlight">Khách cần trả</el-col>
        <el-col :span="10" class="value bold highlight right">{{
          needPay.toLocaleString()
        }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="14" class="label">Khách thanh toán</el-col>
        <el-col :span="10" class="value bold right">
          <el-input v-model="customerPay" size="small" placeholder="0" />
        </el-col>
      </el-row>
    </div>

    <div class="pos-payment-method">
      <el-radio-group v-model="payMethod" size="large">
        <el-radio-button label="Tiền mặt" />
        <el-radio-button label="Chuyển khoản" />
        <el-radio-button label="Thẻ" />
        <el-radio-button label="Ví" />
      </el-radio-group>
    </div>

    <div class="pos-cash-quick">
      <el-row :gutter="8">
        <el-col :span="4" v-for="amount in amounts" :key="amount">
          <el-button
            size="large"
            @click="setCustomerPay(amount)"
            :type="customerPay == amount ? 'primary' : 'default'"
            class="cash-btn"
          >
            {{ amount.toLocaleString() }}
          </el-button>
        </el-col>
      </el-row>
    </div>

    <el-button type="primary" class="btn-pay" size="large" round>
      THANH TOÁN
    </el-button>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";

const storeName = "ILUPET TÔ HIẾU";
const now = new Date().toLocaleString("vi-VN", {
  hour: "2-digit",
  minute: "2-digit",
  day: "2-digit",
  month: "2-digit",
  year: "numeric",
});
const customer = ref("");
const priceList = ref("default");

const total = ref(65000);
const discount = ref(0);
const extra = ref(0);
const customerPay = ref(65000);

const needPay = computed(
  () => total.value - Number(discount.value || 0) + Number(extra.value || 0)
);
const payMethod = ref("Tiền mặt");
const amounts = [65000, 66000, 70000, 80000, 100000, 200000, 500000];

function setCustomerPay(amount) {
  customerPay.value = amount;
}
</script>

<style scoped>
.pos-checkout {
  background: #fff;
  padding: 24px 18px 16px 18px;
  border-radius: 16px;
  max-width: 480px;
  margin: 0 auto;
  box-shadow: 0 4px 18px 0 rgba(0, 0, 0, 0.07);
}

.pos-header {
  margin-bottom: 20px;
}
.input-search {
  width: 100%;
}
.datetime {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  font-size: 13px;
  color: #888;
  gap: 2px;
}
.pos-summary {
  background: #f6f8fa;
  border-radius: 10px;
  padding: 18px 14px 8px 14px;
  margin-bottom: 18px;
  font-size: 15px;
}
.label {
  color: #2b2b2b;
}
.value {
  text-align: right;
}
.bold {
  font-weight: bold;
}
.highlight {
  color: #2579ff;
  font-size: 17px;
}
.right {
  text-align: right;
}
.pos-payment-method {
  margin: 14px 0 10px 0;
  text-align: left;
}
.pos-cash-quick {
  margin-bottom: 18px;
  margin-top: 6px;
}
.cash-btn {
  width: 100%;
  margin-bottom: 8px;
  font-size: 15px;
  font-weight: 500;
}
.btn-pay {
  width: 100%;
  font-size: 18px;
  padding: 14px 0;
  margin-top: 6px;
  letter-spacing: 1px;
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 rgba(51, 143, 255, 0.08);
}
</style>
