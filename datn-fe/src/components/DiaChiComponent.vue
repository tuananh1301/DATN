<template>
  <div class="container">
    <h2>ĐỊA CHỈ GIAO HÀNG</h2>
    <el-form
      :model="form"
      :rules="rules"
      ref="formRef"
      @submit.prevent="saveAddress"
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="Họ tên" prop="fullName">
        <el-input
          v-model="form.fullName"
          placeholder="Nhập họ tên của bạn"
          clearable
        />
      </el-form-item>
      <el-form-item label="SĐT" prop="phone">
        <el-input
          v-model="form.phone"
          placeholder="Nhập số điện thoại"
          clearable
        />
      </el-form-item>
      <el-form-item label="Email" prop="email">
        <el-input v-model="form.email" placeholder="Nhập email" clearable />
      </el-form-item>
      <el-form-item label="Thành / Thị" prop="province">
        <el-select
          v-model="form.provinceId"
          placeholder="Chọn tỉnh/thành phố"
          @change="fetchDistricts"
          clearable
          style="width: 100%"
        >
          <el-option
            v-for="province in provinces"
            :key="province.ProvinceID"
            :label="province.ProvinceName"
            :value="province.ProvinceID"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Quận / Huyện" prop="district">
        <el-select
          v-model="form.districtId"
          placeholder="Chọn quận/huyện"
          @change="fetchWards"
          clearable
          :disabled="!form.provinceId"
          style="width: 100%"
        >
          <el-option
            v-for="district in districts"
            :key="district.DistrictID"
            :label="district.DistrictName"
            :value="district.DistrictID"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Phường / Xã" prop="ward">
        <el-select
          v-model="form.wardCode"
          placeholder="Chọn phường/xã"
          clearable
          :disabled="!form.districtId"
          style="width: 100%"
        >
          <el-option
            v-for="ward in wards"
            :key="ward.WardCode"
            :label="ward.WardName"
            :value="ward.WardCode"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Địa chỉ" prop="address">
        <el-input
          v-model="form.address"
          placeholder="Nhập địa chỉ của bạn"
          clearable
        />
      </el-form-item>
      <el-form-item label="Đặt làm mặc định" prop="isDefault">
        <el-checkbox v-model="form.isDefault" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit">Lưu thông tin</el-button>
      </el-form-item>
    </el-form>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
import { defineComponent, ref, onMounted } from "vue";

export default defineComponent({
  name: "DiaChiComponent",

  setup() {
    const form = ref({
      fullName: "",
      phone: "",
      email: "",
      provinceId: null,
      districtId: null,
      wardCode: null,
      address: "",
      isDefault: false,
    });

    const formRef = ref(null);
    const errorMessage = ref("");
    const provinces = ref([]);
    const districts = ref([]);
    const wards = ref([]);

    const rules = ref({
      fullName: [
        { required: true, message: "Vui lòng nhập họ tên", trigger: "blur" },
      ],
      phone: [
        {
          required: true,
          message: "Vui lòng nhập số điện thoại",
          trigger: "blur",
        },
      ],
      email: [
        { required: true, message: "Vui lòng nhập email", trigger: "blur" },
      ],
      provinceId: [
        {
          required: true,
          message: "Vui lòng chọn tỉnh/thành phố",
          trigger: "change",
        },
      ],
      districtId: [
        {
          required: true,
          message: "Vui lòng chọn quận/huyện",
          trigger: "change",
        },
      ],
      wardCode: [
        {
          required: true,
          message: "Vui lòng chọn phường/xã",
          trigger: "change",
        },
      ],
      address: [
        { required: true, message: "Vui lòng nhập địa chỉ", trigger: "blur" },
      ],
    });

    // Fetch provinces when component mounts
    const fetchProvinces = async () => {
      try {
        const response = await fetch(
          "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province",
          {
            headers: {
              "Content-Type": "application/json",
              Token: "d6e3dccb-6289-11ea-8b85-c60e4edfe802", // Thay bằng token thực tế của bạn
            },
          }
        );
        if (response.ok) {
          const data = await response.json();
          provinces.value = data.data;
        } else {
          errorMessage.value = "Không thể tải danh sách tỉnh/thành phố";
        }
      } catch (error) {
        errorMessage.value = `Lỗi kết nối: ${error.message}`;
      }
    };

    // Fetch districts based on selected province
    const fetchDistricts = async () => {
      if (!form.value.provinceId) {
        districts.value = [];
        form.value.districtId = null;
        wards.value = [];
        form.value.wardCode = null;
        return;
      }
      try {
        const response = await fetch(
          `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${form.value.provinceId}`,
          {
            headers: {
              "Content-Type": "application/json",
              Token: "d6e3dccb-6289-11ea-8b85-c60e4edfe802", // Thay bằng token thực tế của bạn
            },
          }
        );
        if (response.ok) {
          const data = await response.json();
          districts.value = data.data;
          form.value.districtId = null; // Reset district when province changes
          wards.value = [];
          form.value.wardCode = null;
        } else {
          errorMessage.value = "Không thể tải danh sách quận/huyện";
        }
      } catch (error) {
        errorMessage.value = `Lỗi kết nối: ${error.message}`;
      }
    };

    // Fetch wards based on selected district
    const fetchWards = async () => {
      if (!form.value.districtId) {
        wards.value = [];
        form.value.wardCode = null;
        return;
      }
      try {
        const response = await fetch(
          `https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${form.value.districtId}`,
          {
            headers: {
              "Content-Type": "application/json",
              Token: "d6e3dccb-6289-11ea-8b85-c60e4edfe802", // Thay bằng token thực tế của bạn
            },
          }
        );
        if (response.ok) {
          const data = await response.json();
          wards.value = data.data;
          form.value.wardCode = null; // Reset ward when district changes
        } else {
          errorMessage.value = "Không thể tải danh sách phường/xã";
        }
      } catch (error) {
        errorMessage.value = `Lỗi kết nối: ${error.message}`;
      }
    };

    const saveAddress = async () => {
      if (!formRef.value) return;

      formRef.value.validate(async (valid) => {
        if (valid) {
          const diaChiReq = {
            idKhachHang: 1, // Giả định ID khách hàng, thay bằng giá trị thực tế
            idTinh: form.value.provinceId,
            tenTinh:
              provinces.value.find(
                (p) => p.ProvinceID === form.value.provinceId
              )?.ProvinceName || "",
            idQuanHuyen: form.value.districtId,
            tenQuanHuyen:
              districts.value.find(
                (d) => d.DistrictID === form.value.districtId
              )?.DistrictName || "",
            idPhuongXa: null, // GHN dùng WardCode thay vì ID riêng
            tenPhuongXa:
              wards.value.find((w) => w.WardCode === form.value.wardCode)
                ?.WardName || "",
            chiTietDiaChi: form.value.address,
            diaChiMacDinh: form.value.isDefault,
            soDienThoai: form.value.phone,
            email: form.value.email,
            trangThai: true, // Giả định trạng thái mặc định
          };

          try {
            const response = await fetch("http://localhost:8080/api/dia-chi", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(diaChiReq),
            });

            if (response.ok) {
              const result = await response.text();
              alert(result); // "Địa chỉ đã được thêm thành công!"
              resetForm();
            } else {
              const error = await response.text();
              errorMessage.value = `Lỗi: ${error}`;
            }
          } catch (error) {
            errorMessage.value = `Lỗi kết nối: ${error.message}`;
          }
        } else {
          errorMessage.value = "Vui lòng kiểm tra lại thông tin!";
        }
      });
    };

    const resetForm = () => {
      form.value = {
        fullName: "",
        phone: "",
        email: "",
        provinceId: null,
        districtId: null,
        wardCode: null,
        address: "",
        isDefault: false,
      };
      errorMessage.value = "";
      if (formRef.value) formRef.value.resetFields();
    };

    onMounted(() => {
      fetchProvinces();
    });

    return {
      form,
      formRef,
      rules,
      saveAddress,
      errorMessage,
      resetForm,
      provinces,
      districts,
      wards,
      fetchDistricts,
      fetchWards,
    };
  },
});
</script>

<style scoped>
.container {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
}

.error-message {
  color: #ff0000;
  margin-top: 10px;
}
</style>
