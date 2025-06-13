<template>
  <Header />
  <div v-if="userDetails && userDetails.length > 0">
    <el-row type="flex" justify="center" align="middle" style="height: 100vh; background: #f0f2f5">
      <el-col :span="12" :md="8" :lg="6">
        <el-card class="box-card" style="border-radius: 10px;">
          <div style="text-align: center">
            <p>login thành công</p>
            <h1 class="studentName">{{ userDetails[0].studentName }}</h1>
            <p class="point">{{ userDetails[0].point }}</p>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
  <div v-else>
    <el-row type="flex" justify="center" align="middle" style="height: 100vh;">
      <el-col :span="8" style="text-align: center">
        <el-icon class="is-loading" style="font-size: 40px; color: #409eff;">
          <Loading />
        </el-icon>
        <div>Loading ...</div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getToken } from '@/services/LocalStorageService'
import Header from '@/components/header/Header.vue'
import { Loading } from '@element-plus/icons-vue'

const router = useRouter()
const userDetails = ref(null)

async function getUserDetails(accessToken) {
  const response = await fetch(
    "http://localhost:8080/point/api/student/myInfo",
    {
      method: "GET",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }
  )
  const data = await response.json()
  userDetails.value = data.result
}

onMounted(() => {
  const accessToken = getToken()
  if (!accessToken) {
    router.push('/login')
    return
  }
  getUserDetails(accessToken)
})
</script>

