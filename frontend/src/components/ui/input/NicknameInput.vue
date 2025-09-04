<script setup>

const props = defineProps({ 
  nickname: {
    type: String,
    default: ''
  } 
});
const emit = defineEmits(['update:nickname']);

</script>

<template>

  <div class="nickname formControls_area">
    <label class="formControls_label" for="name">Nickname</label>
    <input class="formControls_input" type="text" name="name" id="name" placeholder="請輸入您的暱稱" :value="nickname" @input="emit('update:nickname', $event.target.value)" />
  </div>

</template>


<style scoped lang="scss"></style>
