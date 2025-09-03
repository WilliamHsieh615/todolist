<script setup>

const props = defineProps({ modelValue: String });
const emit = defineEmits(['update:modelValue']);

</script>

<template>

  <div class="email formControls_area">
    <label class="formControls_label" for="email">Email</label>
    <input class="formControls_input" type="email" id="email" name="email" placeholder="請輸入電子信箱" :value="props.modelValue" @input="emit('update:modelValue', $event.target.value)" required>
  </div>

</template>


<style scoped lang="scss"></style>
