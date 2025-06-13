<template>
  <el-row type="flex" justify="center" align="middle" style="height: 100vh; background: #f0f2f5">
    <el-col :span="8" :md="6" :lg="4">
      <el-card class="box-card" style="border-radius: 10px;">
        <div style="text-align: center; margin-bottom: 18px;">
          <span style="font-size: 24px; font-weight: bold;">Welcome to A</span>
        </div>
        <el-form @submit.prevent="handleLogin">
          <el-form-item>
            <el-input v-model="username" placeholder="Username" />
          </el-form-item>
          <el-form-item>
            <el-input v-model="password" placeholder="Password" show-password />
          </el-form-item>
        </el-form>
        <el-button
          type="primary"
          size="large"
          style="width: 100%; margin-bottom: 16px;"
          @click="handleLogin"
        >
          Login
        </el-button>
        <el-button
          type="danger"
          size="large"
          style="width: 100%; margin-bottom: 16px;"
          @click="handleContinueWithGoogle"
        >
          <el-icon class="mr-2"><svg viewBox="0 0 48 48" width="1em" height="1em"><g><circle fill="#fff" cx="24" cy="24" r="24"/><path fill="#4285F4" d="M34.64 24.204c0-.638-.057-1.252-.162-1.84H24v3.484h6.006c-.26 1.4-1.063 2.587-2.264 3.39v2.82h3.652c2.14-1.972 3.246-4.874 3.246-8.017z"/><path fill="#34A853" d="M24 36c2.7 0 4.96-.89 6.613-2.423l-3.652-2.82c-1.01.677-2.3 1.075-3.96 1.075-3.043 0-5.622-2.052-6.546-4.813h-3.785v3.035C16.257 33.917 19.828 36 24 36z"/><path fill="#FBBC05" d="M17.454 27.019c-.226-.677-.356-1.397-.356-2.019 0-.622.13-1.342.356-2.019v-3.035h-3.785A11.985 11.985 0 0 0 12 24c0 1.987.48 3.872 1.317 5.572l4.137-3.035z"/><path fill="#EA4335" d="M24 18.882c1.47 0 2.783.505 3.819 1.498l2.857-2.857C28.957 15.658 26.7 14.57 24 14.57c-4.172 0-7.743 2.083-9.163 5.089l4.137 3.035c.924-2.761 3.503-4.813 6.546-4.813z"/></g></svg></el-icon>
          Continue with Google
        </el-button>
        <el-divider />
        <el-button
          type="success"
          size="large"
          style="width: 100%;"
        >
          Create an account
        </el-button>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { OAuthConfig } from '../configurations/configuration'
import { getToken, setToken } from '@/services/LocalStorageService'

const router = useRouter()
const username = ref('')
const password = ref('')

onMounted(() => {
  const accessToken = getToken()
  if (accessToken) {
    router.push('/')
  }
})

function handleContinueWithGoogle() {
  const callbackUrl = OAuthConfig.redirectUri
  const authUrl = OAuthConfig.authUri
  const googleClientId = OAuthConfig.clientId

  const targetUrl = `${authUrl}?redirect_uri=${encodeURIComponent(
    callbackUrl
  )}&response_type=code&client_id=${googleClientId}&scope=openid%20email%20profile`

  window.location.href = targetUrl
}

function handleLogin(event) {
  if (event) event.preventDefault()
  const data = {
    username: username.value,
    password: password.value,
  }
  fetch('http://localhost:8080/lily-shop/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => {
      setToken(data.result?.token)
      router.push('/')
    })
}
</script>
