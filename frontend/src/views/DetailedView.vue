<script setup lang="ts">

import { ref } from 'vue'
import { useRoute } from 'vue-router'
import router from '@/router'
import { useSnackbarStore } from '@/stores/snackbarStore'

const snackbarStore = useSnackbarStore()

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
      snackbarStore.setErrorSnackbar('Esines viga uue raamatu lisamisel')
      return
    }
    book.value = await response.json()
  } catch (error) {
    snackbarStore.setErrorSnackbar('Serveriga ühindumisel esines viga')
  }
}
fetchBook()

const deleteBook = async () => {
  try {
    await fetch('http://localhost:8080/api/books/' + route.params.id, { method: 'DELETE' })
    snackbarStore.setSuccessSnackbar('Raamatu kustutamine õnnstus!')
    await router.push('/')
  } catch (error) {
    snackbarStore.setErrorSnackbar('Esinest viga raamatu kustutamisel')
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
      if (!response.ok) {
        snackbarStore.setErrorSnackbar('Esinest viga raamatu laenutamisel')
        return
      }
      snackbarStore.setSuccessSnackbar(
        'Raamat ' + (book.value.available ? 'Laenutati' : 'Tagastati')
      )
      await router.push('/')
    })
    .catch(e => {
      snackbarStore.setErrorSnackbar('Esinest viga Serveriga ühendumisel')
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