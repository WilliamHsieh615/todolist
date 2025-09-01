<script setup>

import { ref, computed } from 'vue';
import OtpInput from "vue3-otp-input";

const code = ref("");

</script>

<template>

  <div class="verify formControls_area">
    <label>請輸入四位數驗證碼</label>
    <OtpInput v-model="code" :num-inputs="4" input-type="number" :input-classes="'otp-input'" />
  </div>

</template>


<style scoped lang="scss"></style>
