<script setup lang="ts">

import { ref } from 'vue'

const books = ref([])
const fetchBooks = async () => {
  try {
    const response = await fetch('http://localhost:8080/book')
    if (!response.ok) {
      console.log('response wasnt OK')
    }
    books.value = await response.json() // Update the books variable with the fetched data
  } catch (error) {
    console.error(error)
  }
}

fetchBooks()

</script>

<template>
  <div>
    {{ books }}
  </div>
</template>

<style scoped>

</style>