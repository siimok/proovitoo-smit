<script setup lang="ts">
import BookForm from '@/components/BookForm.vue'
import { ref } from 'vue'
import router from '@/router'

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
  fetch('http://localhost:8080/book/', requestOptions)
    .then(async response => {
      const isJson = response.headers.get('content-type')?.includes('application/json');
      const data = isJson && await response.json();

      // check for error response
      if (!response.ok) {
        // get error message from body or default to response status
        const error = (data && data.message) || response.status;
        return Promise.reject(error);
      }

      await router.push("/");
    })
    .catch(error => {
      console.error("There was an error!", error);
    });
}
</script>

<template>
  <book-form
    :book-data="book"
    @publish="publish()"
  />
</template>

<style scoped>

</style>