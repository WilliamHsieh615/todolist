<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import Swal from 'sweetalert2'

const authStore = useAuthStore()
const router = useRouter()

const nickname = computed(() => authStore.nickname)

// 登出
const logout = () => {
  Swal.fire({
    icon: "warning",
    title: "即將登出",
    text: "確定要登出嗎？",
    confirmButtonText: '確定',
    showCancelButton: true,
    cancelButtonText: '取消'
  }).then((result) => {
    if (result.isConfirmed) {
      authStore.logoutSuccess()

      Swal.fire({
        icon: 'success',
        title: '已登出',
        text: '將傳送至登入頁',
        timer: 2000,
        timerProgressBar: true,
        showConfirmButton: false,
      }).then(() => {
        router.push('/')
      })
    }
  })
}

</script>

<template>

  <nav class="nav">
    <h1>ONLINE TODO LIST</h1>
    <ul>
      <li class="todo_sm"><span>{{ nickname ? nickname + ' 的代辦' : '您的代辦' }}</span></li>
      <li>
        <a href="#" @click.prevent="logout">登出</a>
      </li>
    </ul>
  </nav>

</template>

<style lang="scss" scoped>
.nav {
  display: flex;
  flex-direction: column;
  gap: 30px;

  h1 {
    width: 500px;
    height: 60px;
    background: url(/title.png) no-repeat center/cover;
    display: block;
    text-indent: 101%;
    overflow: hidden;
    white-space: nowrap;
    margin: 0 auto;
  }

  ul {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 20px;

    a {
      text-decoration: none;
      color: $font-color;
      font-size: 16px;

      &:hover {
        color: #000000;
      }
    }
  }
}

@media (max-width: 550px) {
  .nav {
    h1 {
      width: 400px;
      height: 50px;
    }
  }
}

@media (max-width: 450px) {
  .nav {
    h1 {
      width: 300px;
      height: 40px;
    }
  }
}
</style>
