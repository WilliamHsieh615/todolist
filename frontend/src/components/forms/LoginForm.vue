<script setup>

import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import EmailInput from '../ui/input/EmailInput.vue';
import PasswordInput from '../ui/input/PasswordInput.vue';
import axios from 'axios';
import Swal from 'sweetalert2';

const router = useRouter();

const loginData = reactive({
  email: '',
  password: ''
});

const api = 'https://todolist-api.hexschool.io/';

const setCookie = (name, value, days = 1) => {
  const expires = new Date(Date.now() + days * 864e5).toUTCString();
  let cookie = `${name}=${encodeURIComponent(value)}; expires=${expires}; path=/; SameSite=Lax`;
  if (location.protocol === 'https:') {
    cookie += '; Secure';
  }
  document.cookie = cookie;
};

const login = async () => {
  try {
    const result = await axios.post(`${api}users/login`, loginData, { headers: { 'Content-Type': 'application/json' } });
    console.log("登入成功", result);
    console.log(result.data.token);

    setCookie("token", result.data.token, 1);
    setCookie("nickname", result.data.nickname, 1);

    Swal.fire({
      icon: "success",
      title: result.data.nickname + " 登入成功",
      text: "歡迎回來！！",
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

  <form class="formControls" @submit.prevent="login">

    <h2 class="formControls_txt">最實用的線上代辦事項服務</h2>

    <EmailInput v-model="loginData.email" />

    <PasswordInput v-model="loginData.password" />

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
