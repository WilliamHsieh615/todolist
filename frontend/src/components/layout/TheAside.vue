<script setup></script>

<template>

  <aside class="side">
    <router-link to="/"><img class="logo" src="/title.png" alt="logo"></router-link>
    <img class="d-m-n banner" src="/banner.png" alt="workImg">
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
    width: 80%;
  }
}

@media (max-width: 500px) {
  .banner{
    display: none;
  }
  .side {
    width: 100%;
  }
}
</style>
