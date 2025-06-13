<template>
  <el-row type="flex" justify="center" align="middle" style="height: 100vh;">
    <el-col :span="8" style="text-align: center">
      <el-icon class="is-loading" style="font-size: 40px; color: #409eff;">
        <Loading />
      </el-icon>
      <div>Authenticating...</div>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRouter } from 'vue-router'
import { setToken } from '@/services/LocalStorageService'
import { Loading } from '@element-plus/icons-vue'

const isLoggedin = ref(false)
const router = useRouter()

onMounted(() => {
  const accessTokenRegex = /code=([^&]+)/
  const isMatch = window.location.href.match(accessTokenRegex)

  if (isMatch) {
    const authCode = isMatch[1]
    fetch(`http://localhost:8080/lily-shop/auth/outbound/authentication?code=${authCode}`, {
      method: "POST"
    })
      .then(response => response.json())
      .then(data => {
        setToken(data.result?.token)
        isLoggedin.value = true
      })
  }
})

watch(isLoggedin, (newVal) => {
  if (newVal) {
    router.push('/')
  }
})
</script>
