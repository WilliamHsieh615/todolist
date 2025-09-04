<script setup>

const props = defineProps({
  email: {
    type: String,
    default: ''
  }
 });
const emit = defineEmits(['update:email']);

</script>

<template>

  <div class="email formControls_area">
    <label class="formControls_label" for="email">Email</label>
    <input class="formControls_input" type="email" id="email" name="email" placeholder="請輸入電子信箱" :value="email" @input="emit('update:email', $event.target.value)" />
  </div>

</template>


<style scoped lang="scss"></style>
