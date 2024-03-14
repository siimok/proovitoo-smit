<script setup lang="ts">

import { ref } from 'vue'
import { useRoute } from 'vue-router'
import BookForm from '@/components/BookForm.vue'

const route = useRoute()

const book = ref({
  title: '',
  author: '',
  published: '',
  available: true,
  description: ''
})

const fetchBook = async () => {
  try {
    const response = await fetch('http://localhost:8080/book/' + route.params.id)
    if (!response.ok) {
      console.log('response wasnt OK')
    }
    book.value = await response.json() // Update the books variable with the fetched data
  } catch (error) {
    console.error(error)
  }
}

fetchBook()

</script>


<template>
  <main class="flex items-center justify-center flex-col mt-10">
    <div class="max-w-[40rem] w-full p-4">


      <h1 class="text-center font-bold text-4xl mb-7 break-words">{{ book.title }}</h1>
      <h2 class="text-center font-bold text-2xl capitalize break-words">{{ book.author }}</h2>
      <p class="text-center ">{{ book.published }}</p>
      <p class="text-right mb-4">Asukoht:
        <br>
        <span class="font-semibold">{{ book.available ? 'Raamatukogu' : 'Laenutatud' }}
        </span>
      </p>
      <p class="break-words">{{ book.description }}</p>
    </div>
  </main>
</template>

<style scoped>

</style>