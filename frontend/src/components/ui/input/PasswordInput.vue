<script setup>

import {ref} from 'vue';

const props = defineProps({ modelValue: String });
const emit = defineEmits(['update:modelValue']);

const showPassword = ref(false);

</script>

<template>

  <div class="password formControls_area">
    <label class="formControls_label" for="pwd">Password</label>
    <div class="password_input">
      <input class="formControls_input" :type="showPassword ? 'text' : 'password'" name="pwd" id="pwd" placeholder="請輸入密碼" :value="props.modelValue" @input="emit('update:modelValue', $event.target.value)" required>
      <a href="#" class="eye" @click.prevent="showPassword = !showPassword"><i
          :class="showPassword ? 'fa-solid fa-eye' : 'fa-solid fa-eye-slash'"></i></a>
    </div>
  </div>

</template>


<style scoped lang="scss"></style>
