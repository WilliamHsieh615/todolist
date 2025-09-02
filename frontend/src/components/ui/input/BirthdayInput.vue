<script setup>

import {ref} from 'vue';

const birthdayField = ref("")

</script>

<template>

  <div class="birthday formControls_area">
    <label class="formControls_label" for="birthday">Birthday</label>
    <input class="formControls_input" type="date" id="birthday" name="birthday" v-model="birthdayField" required>
  </div>

</template>


<style scoped lang="scss"></style>