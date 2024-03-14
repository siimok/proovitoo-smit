<script setup lang="ts">
import BookList from '@/components/BookList.vue'
import { computed, ref, watch } from 'vue'
import type { Book } from '@/types/types'
import { useSnackbarStore } from '@/stores/snackbarStore'

const snackbarStore = useSnackbarStore()

const books = ref<Array<Book>>([])
const fetchBooks = async (title: string = '') => {
  try {
    const response = await fetch('http://localhost:8080/api/books/?title=' + title)
    if (!response.ok) {
      snackbarStore.setErrorSnackbar('Raamatute pärimisel esines viga')
      return
    }
    books.value = await response.json()
  } catch (error) {
    snackbarStore.setErrorSnackbar('Serveriga ühindumisel esines viga')
  }
}


const showOnlyAvailable = ref(false)

const filteredBooks = computed(() => {
    if (showOnlyAvailable.value) {
      return books.value.filter(book => book.available === true)
    } else return books.value
  }
)


const searchString = ref('')
let timeoutId: number | null = null

watch(searchString, async () => {
  if (timeoutId !== null) {
    clearTimeout(timeoutId as number)
  }

  timeoutId = setTimeout(() => {
    fetchBooks(searchString.value)
  }, 300)
})

fetchBooks()
</script>

<template>
  <main class="flex flex-col items-center justify-center">
    <div class="max-w-[80rem]">
      <div class="w-full mt-4 p-4 text-center justify-center flex flex-col md:flex-row md: md:items-center">
        <input
          v-model="searchString"
          class="w-full max-w-[30rem] h-[2.75rem] border rounded border-gray-800 shadow pl-3 pr-4 py-2.5 text-base"
          maxlength="100"
          placeholder="Tõde ja..."
        >

        <div class="md:ml-5">
          <button
            class="bg-blue-500 w-[14rem] mt-4 md:mt-0 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            @click="showOnlyAvailable = !showOnlyAvailable"
          >
            {{ showOnlyAvailable ? 'Näita kõiki' : 'Näita ainult kohalolevaid' }}
          </button>
        </div>
      </div>

      <book-list :books="filteredBooks" />
    </div>
  </main>
</template>
