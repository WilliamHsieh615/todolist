<script setup>

import EmailInput from '../ui/input/EmailInput.vue';
import CompletePasswordInput from '../ui/input/CompletePasswordInput.vue';
import NicknameInput from '../ui/input/NicknameInput.vue';
import VerifyInput from '../ui/input/VerifyInput.vue';

</script>

<template>

  <form class="formControls" action="index.html">

    <h2 class="formControls_txt">註冊帳號</h2>

    <EmailInput />

    <CompletePasswordInput />

    <NicknameInput />

    <input class="formControls_btnSubmit" type="button" value="寄送驗證碼">

    <VerifyInput />

    <input class="formControls_btnSubmit" type="button" value="註冊">

    <router-link class="formControls_btnLink" to="/">登入</router-link>

  </form>

</template>

<style scoped lang="scss"></style>
