<script setup>

import { ref } from 'vue';

const nicknameField = ref("");

</script>

<template>

  <div class="nickname formControls_area">
    <label class="formControls_label" for="name">Nickname</label>
    <input class="formControls_input" type="text" name="name" id="name" placeholder="請輸入您的暱稱" v-model="nicknameField">
  </div>

</template>


<style scoped lang="scss"></style>
