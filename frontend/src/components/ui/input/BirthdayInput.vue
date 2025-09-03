<script setup>

import { ref, onMounted } from 'vue';
import flatpickr from 'flatpickr'
import 'flatpickr/dist/flatpickr.css'

const birthdayField = ref("")

onMounted(() => {
  flatpickr('#birthday', {
    dateFormat: 'Y/m/d',
    maxDate: 'today',
    allowInput: true,
    disableMobile: true,
    onChange: (selectedDates, dateStr) => {
      birthdayField.value = dateStr
    }
  })
})

</script>

<template>

  <div class="birthday formControls_area">
    <label class="formControls_label" for="birthday">Birthday</label>
    <input id="birthday" type="text" class="formControls_input" placeholder="請選擇生日" v-model="birthdayField" required />
    <ul class="matchTip">
      <li><i class="fa-solid fa-circle-info"></i> 生日僅提供驗證使用</li>
    </ul>
  </div>

</template>


<style scoped lang="scss"></style>