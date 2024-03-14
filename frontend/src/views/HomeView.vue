<script setup lang="ts">
import BookList from '@/components/BookList.vue'
import { ref, watch } from 'vue'
import type { Book } from '@/types/types'

const books = ref<Array<Book>>([])
const fetchBooks = async (title: string = '') => {
  try {
    const response = await fetch('http://localhost:8080/api/book/?title=' + title)
    if (!response.ok) {
      console.log('response wasnt OK')
    }
    books.value = await response.json() // Update the books variable with the fetched data
  } catch (error) {
    console.error(error)
  }
}

const searchString = ref('')
let timeoutId: number | null = null

watch(searchString, async () => {
  // Clear previous timeout
  if (timeoutId !== null) {
    clearTimeout(timeoutId as number) // Type assertion to assert timeoutId is a number
  }

  // Set new timeout
  timeoutId = setTimeout(() => {
    // Perform your composite API call here
    fetchBooks(searchString.value)
  }, 300)
})

fetchBooks()
</script>

<template>
  <main class="flex flex-col items-center justify-center">
    <div class="max-w-[80rem]">
      <div class="w-full p-4 text-center">
        <input
          v-model="searchString"
          class="mt-4 w-full max-w-[30rem] h-[2.75rem] border rounded border-gray-800 shadow pl-3 pr-4 py-2.5 text-base"
          maxlength="100"
          placeholder="Tõde ja..."
        >
      </div>
      <book-list :books />
    </div>
  </main>
</template>
