<script setup lang="ts">

import { ref } from 'vue'
import BookCard from '@/components/BookCard.vue'

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
  <div class="grid grid-cols-4 gap-4">
    <div
      v-for="book in books"
      :key="book.id"
    >
      <book-card :book></book-card>
    </div>
  </div>

</template>

<style scoped>

</style>