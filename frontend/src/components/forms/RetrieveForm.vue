<script setup>

import EmailInput from '../ui/input/EmailInput.vue';
import CompletePasswordInput from '../ui/input/CompletePasswordInput.vue';
import BirthdayInput from '../ui/input/BirthdayInput.vue';

</script>

<template>

  <form class="formControls" action="index.html">

    <h2 class="formControls_txt">重設密碼</h2>

    <EmailInput/>

    <BirthdayInput/>

    <input class="formControls_btnSubmit" type="button" value="驗證">

    <CompletePasswordInput/>

    <!-- 上線使用 -->
    <!-- <input class="formControls_btnSubmit" type="button" value="重設"> -->
    <!-- 開發使用 -->
    <router-link class="formControls_btnSubmit" to="/">重設</router-link>

    <router-link class="formControls_btnLink" to="/">登入</router-link>

  </form>

</template>

<style scoped lang="scss"></style>
