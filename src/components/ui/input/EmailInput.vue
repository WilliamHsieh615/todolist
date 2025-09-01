<script setup>

import { ref } from 'vue';

const emailField = ref("")

</script>

<template>

  <div class="email formControls_area">
    <label class="formControls_label" for="email">Email</label>
    <input class="formControls_input" type="text" id="email" name="email" placeholder="請輸入電子信箱" v-model="emailField"
      required>
  </div>

</template>


<style scoped lang="scss"></style>
