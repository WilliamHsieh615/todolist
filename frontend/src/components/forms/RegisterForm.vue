<script setup>

import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import EmailInput from '../ui/input/EmailInput.vue';
import CompletePasswordInput from '../ui/input/CompletePasswordInput.vue';
import NicknameInput from '../ui/input/NicknameInput.vue';
import BirthdayInput from '../ui/input/BirthdayInput.vue';
import axios from 'axios';
import Swal from 'sweetalert2';

const router = useRouter();

const registerData = reactive({
  email: '',
  password: '',
  nickname: '',
  // birthday: ''
})

const confirmPasswordField = ref('')

const api = 'https://todolist-api.hexschool.io/';

const register = async () => {

  console.log('email:', registerData.email)
  console.log('password:', registerData.password)
  console.log('confirmPassword:', confirmPasswordField.value)
  console.log('nickname:', registerData.nickname)
  console.log('birthday:', registerData.birthday)

  // 檢查是否為空值
  if (!registerData.email || !registerData.password || !registerData.nickname || !registerData.birthday || !confirmPasswordField.value) {
    return Swal.fire({
      icon: 'warning',
      title: '請填寫全部欄位',
      text: '欄位尚未填寫完整',
      confirmButtonColor: "#d33",
    });
  }

  // email 格式檢查
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(registerData.email)) {
    return Swal.fire({
      icon: 'warning',
      title: 'Email 格式不正確',
      text: '請輸入正確的 Email 地址',
      confirmButtonColor: "#d33",
    });
  }

  // 密碼格式檢查
  if (!/\d/.test(registerData.password) ||
    !/[A-Za-z]/.test(registerData.password) ||
    registerData.password.length < 6 ||
    registerData.password.length > 12) {
    return Swal.fire({
      icon: 'warning',
      title: '密碼格式不正確',
      text: '請確認密碼需為 6-12 碼，且包含英文與數字',
      confirmButtonColor: "#d33",
    });
  }

  // 檢查密碼一致
  if (registerData.password !== confirmPasswordField.value) {
    return Swal.fire({
      icon: 'warning',
      title: '密碼不一致',
      text: '請確認兩次輸入的密碼相同',
      confirmButtonColor: "#d33",
    });
  }
  
  // 檢查生日格式
  const birthdayPattern = /^\d{4}\/\d{2}\/\d{2}$/;
  if (!birthdayPattern.test(registerData.birthday)) {
    return Swal.fire({
      icon: 'warning',
      title: '生日格式不正確',
      text: '請輸入正確格式 YYYY/MM/DD 或使用日期選擇器',
      confirmButtonColor: "#d33",
    });
  }

  try {
    const result = await axios.post(`${api}users/sign_up`, registerData, { headers: { 'Content-Type': 'application/json' } });
    console.log("註冊成功", result);
    Swal.fire({
      icon: "success",
      title: `${registerData.nickname} 註冊成功`,
      text: "將傳送至登入頁",
      timer: 2000,
      timerProgressBar: true,
    }).then(() => {
      router.push('/');
    });
  } catch (error) {
    console.error("註冊失敗", error);
    Swal.fire({
      icon: "warning",
      title: "註冊失敗",
      text: error.response.status === 400 ? error.response.data.message : "請稍後再試！！",
      confirmButtonColor: "#d33",
    }).then(() => {
      registerData.email = '';
      registerData.password = '';
      registerData.nickname = '';
      registerData.birthday = '';
      confirmPasswordField.value = '';
    });
  }
}

</script>

<template>

  <form class="formControls" @submit.prevent="register">

    <h2 class="formControls_txt">註冊帳號</h2>

    <EmailInput v-model:email="registerData.email" />

    <CompletePasswordInput v-model:password="registerData.password" v-model:confirmPassword="confirmPasswordField" />

    <NicknameInput v-model:nickname="registerData.nickname" />

    <BirthdayInput v-model:birthday="registerData.birthday" />

    <input class="formControls_btnSubmit" type="submit" value="註冊">

    <router-link class="formControls_btnLink" to="/">登入</router-link>

  </form>

</template>

<style scoped lang="scss"></style>
