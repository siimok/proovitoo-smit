<script setup lang="ts">

import type { PropType } from 'vue'
import type { Book } from '@/types/types'
import BookCard from '@/components/BookCard.vue'

const props = defineProps({ books: { type: Object as PropType<Array<Book>> } })

</script>

<template>
  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 justify-between">
    <div
      v-for="book in books"
      :key="book.id"
    >
      <book-card :book></book-card>
    </div>
    <div
      v-if="books?.length === 0"
      class="text-2xl font-semibold mt-4 text-center"
    >Raamatuid ei ole
    </div>
  </div>
</template>