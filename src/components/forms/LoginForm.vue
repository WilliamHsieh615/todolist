<script setup>

import EmailInput from '../ui/input/EmailInput.vue';
import PasswordInput from '../ui/input/PasswordInput.vue';

</script>

<template>

  <form class="formControls" action="index.html">

    <h2 class="formControls_txt">最實用的線上代辦事項服務</h2>

    <EmailInput />

    <PasswordInput />

    <div class="other-link">
      <router-link class="formControls_btnLink" to="/register">註冊</router-link>
      <span class="line"></span>
      <router-link class="formControls_btnLink" to="/retrieve">忘記密碼</router-link>
    </div>


    <!-- 上線使用 -->
    <!-- <input class="formControls_btnSubmit" type="button" value="登入"> -->
    <!-- 開發使用 -->
    <router-link class="formControls_btnSubmit" to="/internal-page">登入</router-link>


  </form>

</template>

<style scoped lang="scss"></style>
