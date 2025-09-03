<script setup>

const props = defineProps({ modelValue: String });
const emit = defineEmits(['update:modelValue']);

</script>

<template>

  <div class="nickname formControls_area">
    <label class="formControls_label" for="name">Nickname</label>
    <input class="formControls_input" type="text" name="name" id="name" placeholder="請輸入您的暱稱" :value="props.modelValue" @input="emit('update:modelValue', $event.target.value)" required>
  </div>

</template>


<style scoped lang="scss"></style>
