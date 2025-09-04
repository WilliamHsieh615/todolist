<script setup>

import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import EmailInput from '../ui/input/EmailInput.vue';
import CompletePasswordInput from '../ui/input/CompletePasswordInput.vue';
import BirthdayInput from '../ui/input/BirthdayInput.vue';
import axios from 'axios';
import Swal from 'sweetalert2';

const router = useRouter();

const verified = ref(false);

const verifyData = reactive({
  email: '',
  birthday: ''
})

const retrieveData = reactive({
  password: ''
})

const confirmPasswordField = ref('')

const api = 'https://todolist-api.hexschool.io/';

const verify = async () => {

  console.log('email:', verifyData.email)
  console.log('birthday:', verifyData.birthday)
  // 檢查是否為空值
  if (!verifyData.email || !verifyData.birthday) {
    return Swal.fire({
      icon: 'warning',
      title: '請填寫全部欄位',
      text: '欄位尚未填寫完整',
      confirmButtonColor: "#d33",
    });
  }

  // email 格式檢查
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(verifyData.email)) {
    return Swal.fire({
      icon: 'warning',
      title: 'Email 格式不正確',
      text: '請輸入正確的 Email 地址',
      confirmButtonColor: "#d33",
    });
  }

  // 檢查生日格式
  const birthdayPattern = /^\d{4}\/\d{2}\/\d{2}$/;
  if (!birthdayPattern.test(verifyData.birthday)) {
    return Swal.fire({
      icon: 'warning',
      title: '生日格式不正確',
      text: '請輸入正確格式 YYYY/MM/DD 或使用日期選擇器',
      confirmButtonColor: "#d33",
    });
  }

  try {
    const result = await axios.post(`${api}users/verify`, verifyData, { headers: { 'Content-Type': 'application/json' } });
    console.log("驗證成功", result);
    Swal.fire({
      icon: "success",
      title: "驗證成功",
      text: "請設定密碼",
      timer: 2000,
      timerProgressBar: true,
    });
    verified.value = true;
  } catch (error) {
    console.error("驗證失敗", error);
    Swal.fire({
      icon: "warning",
      title: "驗證失敗",
      text: error.response.status === 400 ? error.response.data.message : "請稍後再試！！",
      confirmButtonColor: "#d33",
    }).then(() => {
      verifyData.email = '';
      verifyData.birthday = '';
    });
  }
}

const retrieve = async () => {

  console.log('password:', retrieveData.password)
  console.log('confirmPassword:', confirmPasswordField.value)

  // 密碼格式檢查
  if (!/\d/.test(retrieveData.password) ||
    !/[A-Za-z]/.test(retrieveData.password) ||
    retrieveData.password.length < 6 ||
    retrieveData.password.length > 12) {
    return Swal.fire({
      icon: 'warning',
      title: '密碼格式不正確',
      text: '請確認密碼需為 6-12 碼，且包含英文與數字',
      confirmButtonColor: "#d33",
    });
  }

  // 檢查密碼一致
  if (retrieveData.password !== confirmPasswordField.value) {
    return Swal.fire({
      icon: 'warning',
      title: '密碼不一致',
      text: '請確認兩次輸入的密碼相同',
      confirmButtonColor: "#d33",
    });
  }

  try {
    const result = await axios.post(`${api}users/retrieve`, retrieveData, { headers: { 'Content-Type': 'application/json' } });
    console.log("密碼重設成功", result);
    Swal.fire({
      icon: "success",
      title: "密碼重設成功",
      text: "將傳送至登入頁",
      timer: 2000,
      timerProgressBar: true,
    }).then(() => {
      router.push('/');
    });
  } catch (error) {
    console.error("密碼重設失敗", error);
    Swal.fire({
      icon: "warning",
      title: "密碼重設失敗",
      text: error.response.status === 400 ? error.response.data.message : "請稍後再試！！",
      confirmButtonColor: "#d33",
    }).then(() => {
      retrieveData.password = '';
      confirmPasswordField.value = '';
    });
  }
}

</script>

<template>

  <form class="formControls" @submit.prevent="retrieve">

    <h2 class="formControls_txt">重設密碼</h2>

    <template v-if="!verified">
      <EmailInput v-model:email="verifyData.email" />
      <BirthdayInput v-model:birthday="verifyData.birthday" />
      <input class="formControls_btnSubmit" type="button" value="驗證" @click="verify">
    </template>

    <template v-else>
      <CompletePasswordInput v-model:password="registerData.password" v-model:confirmPassword="confirmPasswordField" />
      <!-- 上線使用 -->
      <input class="formControls_btnSubmit" type="submit" value="重設">
    </template>

    <!-- 開發使用 -->
    <!-- <router-link class="formControls_btnSubmit" to="/">重設</router-link> -->

    <router-link class="formControls_btnLink" to="/">登入</router-link>

  </form>

</template>

<style scoped lang="scss"></style>
