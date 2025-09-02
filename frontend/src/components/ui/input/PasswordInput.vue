<script setup>

import {ref} from 'vue';

const passwordField = ref("");
const showPassword = ref(false);

</script>

<template>

  <div class="password formControls_area">
    <label class="formControls_label" for="pwd">Password</label>
    <div class="password_input">
      <input class="formControls_input" :type="showPassword ? 'text' : 'password'" name="pwd" id="pwd"
             placeholder="請輸入密碼" v-model="passwordField" required>
      <a href="#" class="eye" @click="showPassword = !showPassword"><i
          :class="showPassword ? 'fa-solid fa-eye' : 'fa-solid fa-eye-slash'"></i></a>
    </div>
  </div>

</template>


<style scoped lang="scss"></style>
