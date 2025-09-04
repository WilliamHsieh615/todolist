<script setup>

import { ref, computed } from 'vue';

const props = defineProps({
  password: {
    type: String,
    default: ''
  },
  confirmPassword: {
    type: String,
    default: ''
  }
})
const emit = defineEmits(['update:password', 'update:confirmPassword']);

const showPassword = ref(false);
const showConfirmPassword = ref(false);

const exclamationTip = 'fa-solid fa-circle-exclamation exclamation';
const checkTip = 'fa-solid fa-circle-check check';
const xmarkTip = 'fa-solid fa-circle-xmark xmark';

// 密碼長度檢查
const passwordLengthTip = computed(() => {
  if (props.password.length === 0) {
    return exclamationTip;
  } else if (props.password.length >= 6 && props.password.length <= 12) {
    return checkTip;
  } else {
    return xmarkTip;
  }
});

// 至少一個字母
const passwordContainLetterTip = computed(() => {
  if (props.password.length === 0) {
    return exclamationTip;
  } else if (/[A-Za-z]/.test(props.password)) {
    return checkTip;
  } else {
    return xmarkTip;
  }
});

// 至少一個數字
const passwordContainDigitTip = computed(() => {
  if (props.password.length === 0) {
    return exclamationTip;
  } else if (/\d/.test(props.password)) {
    return checkTip;
  } else {
    return xmarkTip;
  }
})

// 密碼是否一致
const passwordMatchTip = computed(() => {
  if (props.confirmPassword.length === 0) {
    return exclamationTip;
  } else if (props.password === props.confirmPassword) {
    return checkTip;
  } else {
    return xmarkTip;
  }
})

</script>

<template>

  <div class="password formControls_area">
    <label class="formControls_label" for="password">Password</label>
    <div class="password_input">
      <input class="formControls_input" :type="showPassword ? 'text' : 'password'" name="password" id="password"
        placeholder="請輸入密碼" :value="password" @input="emit('update:password', $event.target.value)" />
      <a href="#" class="eye" @click.prevent="showPassword = !showPassword"><i
          :class="showPassword ? 'fa-solid fa-eye' : 'fa-solid fa-eye-slash'"></i></a>
    </div>
    <div class="password_input">
      <input class="formControls_input" :type="showConfirmPassword ? 'text' : 'password'" name="confirm-password"
        id="confirm-password" placeholder="請再次輸入密碼" :value="confirmPassword"
        @input="emit('update:confirmPassword', $event.target.value)" />
      <a href="#" class="eye" @click.prevent="showConfirmPassword = !showConfirmPassword"><i
          :class="showConfirmPassword ? 'fa-solid fa-eye' : 'fa-solid fa-eye-slash'"></i></a>
    </div>
    <ul class="matchTip">
      <li><i :class="passwordLengthTip"></i> 長度需為 6~12 碼</li>
      <li><i :class="passwordContainLetterTip"></i> 至少 1 個英文字母</li>
      <li><i :class="passwordContainDigitTip"></i> 至少 1 個數字</li>
      <li><i :class="passwordMatchTip"></i> 密碼是否一致</li>
    </ul>
  </div>

</template>


<style scoped lang="scss"></style>
