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

const api = 'https://todolist-api.hexschool.io/';

const verify = async () => {
  try {
    const result = await axios.post(`${api}users/verify`, verifyData, { headers: { 'Content-Type': 'application/json' } });
    console.log("驗證成功", result);
    Swal.fire({
      icon: "success",
      title: "驗證成功",
      text: "請設定密碼",
      confirmButtonColor: "#4f46e5",
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
  try {
    const result = await axios.post(`${api}users/retrieve`, retrieveData, { headers: { 'Content-Type': 'application/json' } });
    console.log("密碼重設成功", result);
    Swal.fire({
      icon: "success",
      title: "密碼重設成功",
      text: "將傳送至登入頁",
      confirmButtonColor: "#4f46e5",
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
    });
  }
}

</script>

<template>

  <form class="formControls" @submit.prevent="retrieve">

    <h2 class="formControls_txt">重設密碼</h2>

    <template v-if="!verified">
      <EmailInput v-model="verifyData.email" />
      <BirthdayInput v-model="verifyData.birthday" />
      <input class="formControls_btnSubmit" type="button" value="驗證" @click="verify">
    </template>

    <template v-else>
      <CompletePasswordInput v-model="retrieveData.password" />
      <!-- 上線使用 -->
      <input class="formControls_btnSubmit" type="submit" value="重設">
    </template>

    <!-- 開發使用 -->
    <!-- <router-link class="formControls_btnSubmit" to="/">重設</router-link> -->

    <router-link class="formControls_btnLink" to="/">登入</router-link>

  </form>

</template>

<style scoped lang="scss"></style>
