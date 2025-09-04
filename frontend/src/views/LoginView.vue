<script setup>

import { onMounted } from 'vue'
import { useRoute } from 'vue-router'
import Swal from 'sweetalert2'

import TheAside from '@/components/layout/TheAside.vue'
import LoginForm from '@/components/forms/LoginForm.vue';

const route = useRoute()

onMounted(() => {
  if (route.query.message === 'not-logged-in') {
    Swal.fire({
      icon: 'warning',
      title: '尚未登入',
      text: '請先登入帳號！',
      confirmButtonColor: '#d33'
    })
  }
})

</script>

<template>

  <div class="wrap bg-yellow">

    <div id="loginPage" class="container loginPage">

      <TheAside/>

      <LoginForm/>

    </div>

  </div>

</template>

<style scoped lang="scss"></style>
