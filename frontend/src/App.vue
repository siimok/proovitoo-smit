<script setup lang="ts">
import TheSnackbar from '@/components/TheSnackbar.vue'
</script>

<template>
  <header>
    <nav class="bg-sky-500 h-14 text-center font-semibold text-2xl pt-3">
      <router-link class="m-4" to="/">Pealeht</router-link>
      <router-link class="m-4" to="/create">Lisa uus</router-link>
    </nav>
  </header>

  <RouterView />

  <the-snackbar/>
</template>
