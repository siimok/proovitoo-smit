<script setup lang="ts">
import BookForm from '@/components/BookForm.vue'
import { ref } from 'vue'

const book = ref({
  title: '',
  author: '',
  published: 2024,
  available: '',
  description: ''
})

function publish() {
  console.log('published')
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