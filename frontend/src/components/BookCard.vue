<script setup lang="ts">
import type { PropType } from 'vue'
import type { Book } from '@/types/types'

const props = defineProps({ book: { type: Object as PropType<Book> } })
</script>

<template>
  <router-link :to="'/book/' + book?.id">
    <div class="relative border border-gray-200 rounded-lg shadow hover:bg-gray-100 w-72 h-64 p-4 m-4">
      <div
        class="absolute right-2 top-2  w-5 h-5 rounded-full"
        :class="book?.available ? 'bg-green-600' : 'bg-red-600'"
      ></div>

      <h2 class="h-16 text-center font-bold text-2xl capitalize overflow-ellipsis overflow-hidden">
        {{ book?.title }}
      </h2>

      <h3 class="text-center capitalize font-semibold text-xl mt-3 truncate">
        {{ book?.author }}
      </h3>

      <h3 class="text-right mt-2">
        Avaldatud: {{ book?.published }}
      </h3>

      <p class="h-[4.5rem] capitalize text-ellipsis overflow-hidden">
        {{ book?.description }}
      </p>
    </div>
  </router-link>
</template>