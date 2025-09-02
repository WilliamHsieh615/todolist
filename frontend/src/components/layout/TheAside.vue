<script setup></script>

<template>

  <aside class="side">
    <router-link to="/"><img class="logo" src="/public/title.png" alt="logo"></router-link>
    <img class="d-m-n" src="/public/banner.png" alt="workImg">
  </aside>

</template>

<style scoped lang="scss">
.side {
  width: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

@media (max-width: 800px) {
  .side {
    width: 60%;
  }
}

@media (max-width: 500px) {
  .side {
    width: 80%;
  }
}
</style>
