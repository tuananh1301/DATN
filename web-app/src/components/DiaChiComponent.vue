```vue
<template>
  <el-form
    :model="form"
    :rules="rules"
    ref="formRef"
    label-width="auto"
    style="max-width: 600px"
  >
    <el-form-item label="Họ tên" prop="fullName">
      <el-input v-model="form.fullName" placeholder="Nhập họ tên của bạn" />
    </el-form-item>

    <el-form-item label="SĐT" prop="phone">
      <el-input v-model="form.phone" placeholder="Nhập số điện thoại" />
    </el-form-item>

    <el-form-item label="Email" prop="email">
      <el-input v-model="form.email" placeholder="Nhập email" />
    </el-form-item>

    <el-form-item label="Tỉnh / Thành phố" prop="province">
      <el-select
        v-model="form.province"
        placeholder="Chọn tỉnh/thành phố"
        value-key="ProvinceID"
        @change="onProvinceChange"
      >
        <el-option
          v-for="item in provinces"
          :key="item.ProvinceID"
          :label="item.ProvinceName"
          :value="item"
        />
      </el-select>
    </el-form-item>

    <el-form-item label="Quận / Huyện" prop="district">
      <el-select
        v-model="form.district"
        placeholder="Chọn quận/huyện"
        :disabled="districts.length === 0"
        @change="onDistrictChange"
      >
        <el-option
          v-for="item in districts"
          :key="item.DistrictID"
          :label="item.DistrictName"
          :value="item"
        />
      </el-select>
    </el-form-item>

    <el-form-item label="Phường / Xã" prop="ward">
      <el-select
        v-model="form.ward"
        placeholder="Chọn phường/xã"
        :disabled="wards.length === 0"
      >
        <el-option
          v-for="item in wards"
          :key="item.WardCode"
          :label="item.WardName"
          :value="item"
        />
      </el-select>
    </el-form-item>

    <el-form-item label="Địa chỉ" prop="address">
      <el-input v-model="form.address" placeholder="Nhập địa chỉ cụ thể" />
    </el-form-item>

    <el-form-item label="Đặt làm mặc định">
      <el-switch v-model="form.defaultAddress" />
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">Lưu thông tin</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { reactive, ref, onMounted } from 'vue';

export default {
  name: 'ShippingAddressForm',
  setup() {
    const formRef = ref(null);

    const form = reactive({
      fullName: '',
      phone: '',
      email: '',
      province: null,
      district: null,
      ward: null,
      address: '',
      defaultAddress: false
    });

    // Hàm validate số điện thoại
    const validatePhone = (rule, value, callback) => {
      if (!value) {
        callback(new Error('Vui lòng nhập số điện thoại'));
      } else {
        const phoneRegex = /^(0|\+84)(3[2-9]|5[689]|7[06-9]|8[1-689]|9[0-46-9])[0-9]{7}$/;
        if (!phoneRegex.test(value)) {
          callback(new Error('Số điện thoại không hợp lệ'));
        } else {
          callback();
        }
      }
    };

    const rules = {
      fullName: [
        { required: true, message: 'Vui lòng nhập họ tên' }
      ],
      phone: [
        { required: true, message: 'Vui lòng nhập số điện thoại' },
        { validator: validatePhone }
      ],
      email: [
        { required: true, message: 'Vui lòng nhập email' },
        { type: 'email', message: 'Email không đúng định dạng' }
      ],
      province: [
        { required: true, message: 'Chọn tỉnh/thành phố' }
      ],
      district: [
        { required: true, message: 'Chọn quận/huyện' }
      ],
      ward: [
        { required: true, message: 'Chọn phường/xã' }
      ],
      address: [
        { required: true, message: 'Nhập địa chỉ cụ thể' }
      ]
    };

    const provinces = ref([]);
    const districts = ref([]);
    const wards = ref([]);

    const GHN_TOKEN = '847c9bb7-6e42-11ee-a59f-a260851ba65c';
    const API_BASE_URL = 'http://localhost:8080/api'; // Thay đổi nếu cần

    const fetchProvinces = async () => {
      try {
        const res = await fetch('https://online-gateway.ghn.vn/shiip/public-api/master-data/province', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Token: GHN_TOKEN
          }
        });
        const json = await res.json();
        console.log('Provinces data:', json.data);
        provinces.value = json.data || [];
      } catch (error) {
        console.error('Error fetching provinces:', error);
      }
    };

    const onProvinceChange = async () => {
      if (!form.province || !form.province.ProvinceID) {
        console.warn('No province selected or invalid ProvinceID');
        return;
      }
      console.log('Selected province:', form.province);
      form.district = null;
      form.ward = null;
      wards.value = [];
      districts.value = [];

      try {
        const res = await fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${form.province.ProvinceID}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Token: GHN_TOKEN
          }
        });
        const json = await res.json();
        console.log('Districts data:', json.data);
        districts.value = json.data || [];
      } catch (error) {
        console.error('Error fetching districts:', error);
      }
    };

    const onDistrictChange = async () => {
      if (!form.district || !form.district.DistrictID) {
        console.warn('No district selected or invalid DistrictID');
        return;
      }
      console.log('Selected district:', form.district);
      form.ward = null;
      wards.value = [];

      try {
        const res = await fetch(`https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${form.district.DistrictID}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Token: GHN_TOKEN
          }
        });
        const json = await res.json();
        console.log('Wards data:', json.data);
        wards.value = json.data || [];
      } catch (error) {
        console.error('Error fetching wards:', error);
      }
    };

    const onSubmit = async () => {
      try {
        const valid = await formRef.value.validate();
        if (!valid) {
          console.log('Form validate failed');
          return;
        }

        const diaChiReq = {
          idTinh: form.province?.ProvinceID || null,
          tenTinh: form.province?.ProvinceName || '',
          idQuanHuyen: form.district?.DistrictID || null,
          tenQuanHuyen: form.district?.DistrictName || '',
          idPhuongXa: form.ward?.WardCode || '',
          tenPhuongXa: form.ward?.WardName || '',
          chiTietDiaChi: form.address || '',
          diaChiMacDinh: form.defaultAddress || false,
          soDienThoai: form.phone || '',
          email: form.email || '',
          tenKhachHang: form.fullName || ''
        };

        console.log('Sending diaChiReq:', diaChiReq);

        const response = await fetch(`http://localhost:8080/lily-shop/location/add`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
            // Thêm token xác thực nếu cần, ví dụ:
            // 'Authorization': `Bearer ${yourToken}`
          },
          body: JSON.stringify(diaChiReq)
        });

        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.message || `HTTP error! Status: ${response.status}`);
        }

        alert('Địa chỉ đã được lưu thành công!');
        formRef.value.resetFields(); // Reset form sau khi lưu thành công
      } catch (error) {
        console.error('Error saving address:', error);
        alert('Lỗi khi lưu địa chỉ: ' + error.message);
      }
    };

    onMounted(async () => {
      await fetchProvinces();
      console.log('Initial provinces:', provinces.value);
    });

    return {
      form,
      rules,
      formRef,
      provinces,
      districts,
      wards,
      onProvinceChange,
      onDistrictChange,
      onSubmit
    };
  }
};
</script>
```