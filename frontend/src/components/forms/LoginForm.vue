<script setup>

import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import EmailInput from '../ui/input/EmailInput.vue';
import PasswordInput from '../ui/input/PasswordInput.vue';
import Swal from 'sweetalert2';
import { login } from '@/plugins/members';

import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore()
const router = useRouter();


const loginData = reactive({
  email: '',
  password: ''
});

const renderLogin = async () => {

  // console.log('email:', loginData.email)
  // console.log('password:', loginData.password)

  // 檢查是否為空值
  if (!loginData.email || !loginData.password) {
    return Swal.fire({
      icon: 'warning',
      title: '請填寫全部欄位',
      text: '欄位尚未填寫完整',
      confirmButtonColor: "#d33",
    });
  }

  // email 格式檢查
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(loginData.email)) {
    return Swal.fire({
      icon: 'warning',
      title: 'Email 格式不正確',
      text: '請輸入正確的 Email 地址',
      confirmButtonColor: "#d33",
    });
  }

  // 密碼格式檢查
  if (!/\d/.test(loginData.password) ||
    !/[A-Za-z]/.test(loginData.password) ||
    loginData.password.length < 6 ||
    loginData.password.length > 12) {
    return Swal.fire({
      icon: 'warning',
      title: '密碼格式不正確',
      text: '請確認密碼需為 6-12 碼，且包含英文與數字',
      confirmButtonColor: "#d33",
    });
  }

  try {
    const result = await login(loginData);
    authStore.loginSuccess(result.data.nickname, result.data.memberId)
    console.log("登入成功", result);
    Swal.fire({
      icon: "success",
      title: "登入成功",
      text: `${result.data.nickname} 歡迎回來！`,
      confirmButtonColor: "#4f46e5",
      timer: 2000,
      timerProgressBar: true,
    }).then(() => {
      router.push('/todolist-page');
    });

  } catch (error) {
    console.error("登入失敗", error);
    let message;
    if (error.response && (error.response.status === 400 ||
      error.response.status === 401 ||
      error.response.status === 404)) {
      message = error.response.data.message;
    } else {
      message = "請稍後再試！！";
    }

    Swal.fire({
      icon: "warning",
      title: "登入失敗",
      text: message,
      confirmButtonColor: "#d33",
    }).then(() => {
      loginData.email = '';
      loginData.password = '';
    });
  }
}

</script>

<template>

  <form class="formControls" @submit.prevent="renderLogin">

    <h2 class="formControls_txt">最實用的線上代辦事項服務</h2>

    <EmailInput v-model:email="loginData.email" />

    <PasswordInput v-model:password="loginData.password" />

    <div class="other-link">
      <router-link class="formControls_btnLink" to="/register">註冊</router-link>
      <span class="line"></span>
      <router-link class="formControls_btnLink" to="/retrieve">忘記密碼</router-link>
    </div>


    <!-- 上線使用 -->
    <input class="formControls_btnSubmit" type="submit" value="登入">
    <!-- 開發使用 -->
    <!-- <router-link class="formControls_btnSubmit" to="/todolist-page">登入</router-link> -->


  </form>

</template>

<style scoped lang="scss"></style>
