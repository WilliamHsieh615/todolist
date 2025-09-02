<script setup>

import {onMounted} from 'vue'
import {useRouter} from 'vue-router'

const router = useRouter()

onMounted(() => {
  setTimeout(() => {
    router.push('/');
  }, 3000)
})

</script>

<template>

  <div class="wrap bg-yellow">

    <div class="container">

      <p>此頁面不存在，正在導回登入頁...</p>

    </div>

  </div>

</template>

<style lang="scss" scoped>
p {
  font-size: 28px;
  font-weight: bold;
}
</style>