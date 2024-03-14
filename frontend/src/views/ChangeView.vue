<script setup lang="ts">

import { ref } from 'vue'
import { useRoute } from 'vue-router'
import BookForm from '@/components/BookForm.vue'
import router from '@/router'

const route = useRoute()

const book = ref({
  id: -1,
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

function publish() {
  const requestOptions = {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(book.value)
  }
  fetch('http://localhost:8080/api/books/' + book.value.id, requestOptions)
    .then(async response => {
      const isJson = response.headers.get('content-type')?.includes('application/json')
      const data = isJson && await response.json()

      if (!response.ok) {
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
  <main class="flex items-center justify-center flex-col mt-10 ">
    <h1 class="font-bold text-3xl mb-7">
      Lisa uus raamat
    </h1>

    <book-form
      :book-data="book"
      @publish="publish()"
    />
  </main>
</template>