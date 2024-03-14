<script setup lang="ts">

import { ref } from 'vue'
import { useRoute } from 'vue-router'
import router from '@/router'

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
    const response = await fetch('http://localhost:8080/api/books/' + route.params.id)
    if (!response.ok) {
      console.log('response wasnt OK')
    }
    book.value = await response.json() // Update the books variable with the fetched data
  } catch (error) {
    console.error(error)
  }
}
fetchBook()

const deleteBook = async () => {
  try {
    await fetch('http://localhost:8080/api/books/' + route.params.id, { method: 'DELETE' });
    console.log('Book deleted successfully.');
  } catch (error) {
    console.error('Error deleting book:', error);
  }
}
fetchBook()

const changeBookAvailability = async () => {
  const requestOptions = {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ availability: !book.value.available })
  }
  fetch('http://localhost:8080/api/books/' + route.params.id + '/availability', requestOptions)
    .then(async response => {
      const isJson = response.headers.get('content-type')?.includes('application/json')
      const data = isJson && await response.json()

      // check for error response
      if (!response.ok) {
        // get error message from body or default to response status
        const error = (data && data.message) || response.status
        return Promise.reject(error)
      }

      await router.push('/')
    })
    .catch(error => {
      console.error('There was an error!', error)
    })
}

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

      <div class="flex justify-between mt-10">
        <button
          class="mt-6 bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded"
          @click="changeBookAvailability"
        >
          {{ book.available ? 'Laenuta' : 'Tagasta' }}
        </button>

        <router-link :to="'/modify/' + route.params.id">
          <button class="mt-6 bg-orange-500 hover:bg-orange-600 text-white font-bold py-2 px-4 rounded">
            Muuda
          </button>
        </router-link>

        <button
          class="mt-6 bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded"
          @click="deleteBook"
        >
          Kustuta
        </button>
      </div>
    </div>
  </main>
</template>