<script setup>

import { reactive } from 'vue';
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
  birthday: ''
})

const api = 'https://todolist-api.hexschool.io/';

const register = async () => {
  try {
    const result = await axios.post(`${api}users/register`, registerData, { headers: { 'Content-Type': 'application/json' } });
    console.log("註冊成功", result);
    Swal.fire({
      icon: "success",
      title: `${registerData.nickname} 註冊成功`,
      text: "將傳送至登入頁",
      confirmButtonColor: "#4f46e5",
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
    });
  }
}

</script>

<template>

  <form class="formControls" @submit.prevent="register">

    <h2 class="formControls_txt">註冊帳號</h2>

    <EmailInput v-model="registerData.email" />

    <CompletePasswordInput v-model="registerData.password" />

    <NicknameInput v-model="registerData.nickname" />

    <BirthdayInput v-model="registerData.birthday" />

    <input class="formControls_btnSubmit" type="submit" value="註冊">

    <router-link class="formControls_btnLink" to="/">登入</router-link>

  </form>

</template>

<style scoped lang="scss"></style>
