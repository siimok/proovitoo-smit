<script setup lang="ts">

import { ref } from 'vue'
import { useRoute } from 'vue-router'
import BookForm from '@/components/BookForm.vue'
import router from '@/router'

const route = useRoute()

import { useSnackbarStore } from '@/stores/snackbarStore'

const snackbarStore = useSnackbarStore()

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
      snackbarStore.setErrorSnackbar('Esines viga raamatu pärimisel')
    }
    book.value = await response.json()
  } catch (error) {
    snackbarStore.setErrorSnackbar('Esines viga serveriga ühendumisel')
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
      if (!response.ok) {
        snackbarStore.setErrorSnackbar('Raamatu muutmisel esines viga')
        return
      }
      snackbarStore.setSuccessSnackbar("Raamatu muutmine õnnestus")
      await router.push('/')
    })
    .catch(e => {
      snackbarStore.setErrorSnackbar('Esines viga serveriga ühendumisel')
    })
}

</script>

<template>
  <main class="flex items-center justify-center flex-col mt-10 ">
    <div class="p-4 md:p-8 max-w-[45rem] w-full border border-gray-200 rounded-lg shadow">
      <h1 class="font-bold text-3xl mb-7">
        Lisa uus raamat
      </h1>

      <book-form
        :book-data="book"
        @publish="publish()"
      />
    </div>

  </main>
</template>