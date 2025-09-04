<script setup>

import { onMounted } from 'vue';
import flatpickr from 'flatpickr'
import 'flatpickr/dist/flatpickr.css'

const props = defineProps({
  birthday: {
    type: String,
    default: ''
  }
})
const emit = defineEmits(['update:birthday'])

onMounted(() => {
  flatpickr('#birthday', {
    dateFormat: 'Y/m/d',
    maxDate: 'today',
    allowInput: true,
    disableMobile: true,
    onChange: (selectedDates, dateStr) => {
      emit('update:birthday', dateStr);
    }
  })
})

</script>

<template>

  <div class="birthday formControls_area">
    <label class="formControls_label" for="birthday">Birthday</label>
    <input id="birthday" type="text" class="formControls_input" placeholder="請選擇生日" :value="birthday" @input="emit('update:birthday', $event.target.value)"/>
    <ul class="matchTip">
      <li><i class="fa-solid fa-circle-info"></i> 生日僅提供驗證使用</li>
    </ul>
  </div>

</template>


<style scoped lang="scss"></style>