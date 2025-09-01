<script setup></script>

<template>

  <nav class="nav">
    <h1>ONLINE TODO LIST</h1>
    <ul>
      <li class="todo_sm"><span>王小明的代辦</span></li>
      <li><router-link to="/">登出</router-link></li>
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
    background: url(/public/title.png) no-repeat center/cover;
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
