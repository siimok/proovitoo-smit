<script setup lang="ts">
import BookForm from '@/components/BookForm.vue'
import { ref } from 'vue'
import router from '@/router'
import { useSnackbarStore } from '@/stores/snackbarStore'

const snackbarStore = useSnackbarStore()

const book = ref({
  title: '',
  author: '',
  published: 2024,
  available: true,
  description: ''
})

function publish() {
  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(book.value)
  };
  fetch('http://localhost:8080/api/books/', requestOptions)
    .then(async response => {

      if (!response.ok) {
        snackbarStore.setErrorSnackbar("Esinest viga uue raamatu lisamisel")
        return
      }

      snackbarStore.setSuccessSnackbar("Raamatu lisamine õnnestus!")
      await router.push("/");
    })
    .catch(e => {
      snackbarStore.setErrorSnackbar("Esinest viga Serveriga ühendumisel")
    });
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