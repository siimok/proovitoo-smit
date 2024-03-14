<script setup lang="ts">

import { ref } from 'vue'
import BookCard from '@/components/BookCard.vue'
import type { Book } from '@/types/types'

const books = ref<Array<Book>>([])
const fetchBooks = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/book/?title=')
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
  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 justify-between">
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