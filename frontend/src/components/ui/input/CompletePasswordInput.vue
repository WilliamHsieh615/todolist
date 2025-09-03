<script setup>

import {ref} from 'vue';

const props = defineProps({ modelValue: String });
const emit = defineEmits(['update:modelValue']);

const showPassword = ref(false);
const showConfirmPassword = ref(false);

const passwordField = ref("");
const confirmpasswordField = ref("");

const exclamationTip = 'fa-solid fa-circle-exclamation exclamation';
const checkTip = 'fa-solid fa-circle-check check';
const xmarkTip = 'fa-solid fa-circle-xmark xmark';

// 密碼長度檢查
const passwordLengthTip = () => {
  if (passwordField.value.length === 0) {
    return exclamationTip;
  } else if (passwordField.value.length >= 6 && passwordField.value.length <= 12) {
    return checkTip;
  } else {
    return xmarkTip;
  }
}

// 至少一個字母
const passwordContainLetterTip = () => {
  if (passwordField.value.length === 0) {
    return exclamationTip;
  } else if (/[A-Za-z]/.test(passwordField.value)) {
    return checkTip;
  } else {
    return xmarkTip;
  }
}

// 至少一個數字
const passwordContainDigitTip = () => {
  if (passwordField.value.length === 0) {
    return exclamationTip;
  } else if (/\d/.test(passwordField.value)) {
    return checkTip;
  } else {
    return xmarkTip;
  }
}

// 密碼是否一致
const passwordMatchTip = () => {
  if (confirmpasswordField.value.length === 0) {
    return exclamationTip;
  } else if (passwordField.value === confirmpasswordField.value) {
    return checkTip;
  } else {
    return xmarkTip;
  }
}

</script>

<template>

  <div class="password formControls_area">
    <label class="formControls_label" for="password">Password</label>
    <div class="password_input">
      <input class="formControls_input" :type="showPassword ? 'text' : 'password'" name="password" id="password"
             placeholder="請輸入密碼" v-model="passwordField" @input="emit('update:modelValue', passwordField)" required>
      <a href="#" class="eye" @click.prevent="showPassword = !showPassword"><i
          :class="showPassword ? 'fa-solid fa-eye' : 'fa-solid fa-eye-slash'"></i></a>
    </div>
    <div class="password_input">
      <input class="formControls_input" :type="showConfirmPassword ? 'text' : 'password'" name="confirm-password"
             id="confirm-password" placeholder="請再次輸入密碼" v-model="confirmpasswordField" required>
      <a href="#" class="eye" @click.prevent="showConfirmPassword = !showConfirmPassword"><i
          :class="showConfirmPassword ? 'fa-solid fa-eye' : 'fa-solid fa-eye-slash'"></i></a>
    </div>
    <ul class="matchTip">
      <li><i :class="passwordLengthTip()"></i> 長度需為 6~12 碼</li>
      <li><i :class="passwordContainLetterTip()"></i> 至少 1 個英文字母</li>
      <li><i :class="passwordContainDigitTip()"></i> 至少 1 個數字</li>
      <li><i :class="passwordMatchTip()"></i> 密碼是否一致</li>
    </ul>
  </div>

</template>


<style scoped lang="scss"></style>
