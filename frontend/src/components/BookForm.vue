<script setup lang="ts">

import { computed, ref } from 'vue'
import { useSnackbarStore } from '@/stores/snackbarStore'

const snackbarStore = useSnackbarStore()

const book = defineModel<any>('bookData')
const emit = defineEmits(['publish'])

const validationErrors = ref({
  title: '',
  author: '',
  published: '',
  description: ''

})

const validateError = computed(() => {
  return !!(
    validationErrors.value.title ||
    validationErrors.value.author ||
    validationErrors.value.description ||
    validationErrors.value.published
  )
})

function validate() {
  // Validate title length
  if (book.value.title.length < 5) {
    validationErrors.value.title = 'Raamatu pealkiri peab olema vähemalt 5 tähemärki'
  } else if (book.value.title.length > 100) {
    validationErrors.value.title = 'Raamatu pealkiri peab olema alla 100 tähemärgi'
  }

  // Validate author length
  if (book.value.author.length < 5) {
    validationErrors.value.author = 'Autori nimi peab olema vähemalt 5 tähemärki'
  } else if (book.value.author.length > 100) {
    validationErrors.value.author = 'Autori nimi peab olema alla 100 tähemärgi'
  }

  // Validate published year
  const publishedYear = parseInt(book.value.published);
  if (isNaN(publishedYear)) {
    validationErrors.value.published = 'Aasta pole määratud';
  } else if (publishedYear < 0) {
    validationErrors.value.published = 'Raamat peab olema noorem kui Kristus';
  } else if (publishedYear > 2025) {
    validationErrors.value.published = 'Ajarändureid me ei salli.';
  }

  // Validate description length
  if (book.value.description.length > 1000) {
    validationErrors.value.description = 'Kirjeldus peab olema alla 1000 tähemärgi'
  }

  if (!validateError.value) {
    emit('publish')
  } else {
    snackbarStore.setErrorSnackbar('Palun paranda väli/väljad')
  }
}

</script>

<template>
  <div>
    <div>
      <div class="flex justify-between">
        <label class="block text-lg font-medium">
          Raamatu nimi
        </label>

        <span class="inline-block text-sm text-gray-600 font-semibold ml-auto">100 tähemärki</span>
      </div>

      <input
        v-model="book.title"
        class="mt-2 w-full h-[2.75rem] border rounded border-default pl-3 pr-4 py-2.5 text-base"
        maxlength="100"
        @blur="validationErrors.title = ''"
      >
    </div>

    <div class="h-8 text-red-600">
      {{ validationErrors.title }}
    </div>

    <div>
      <div class="flex justify-between">
        <label class="block text-lg font-medium">
          Autor
        </label>

        <span class="inline-block text-sm text-gray-600 font-semibold ml-auto">100 tähemärki</span>
      </div>

      <input
        v-model="book.author"
        class="mt-2 w-full h-[2.75rem] border rounded border-default pl-3 pr-4 py-2.5 text-base"
        maxlength="100"
        @blur="validationErrors.author = ''"
      >
    </div>

    <div class="h-8 text-red-600">
      {{ validationErrors.author }}
    </div>

    <div>
      <div class="flex justify-between">
        <label class="block text-lg font-medium">
          Avaldamise aasta
        </label>
      </div>

      <input
        v-model="book.published"
        class="mt-2 w-full h-[2.75rem] border rounded border-default pl-3 pr-4 py-2.5 text-base"
        type="text" pattern="[0-9]{4}"
        placeholder="YYYY"
        maxlength="4"
        required
        @blur="validationErrors.published = ''"
      >
    </div>

    <div class="h-8 text-red-600">
      {{ validationErrors.published }}
    </div>

    <div class="flex">
      <input
        v-model="book.available"
        class="h-6 w-6 mr-2"
        type="checkbox"
        id="available"
      />
      <label
        for="available"
        class="flex text-sm font-semibold items-center"
      >Kohal
      </label>
    </div>

    <div class="mt-6">
      <div class="flex justify-between">
        <label class="block text-lg font-medium">
          Kirjeldus
        </label>

        <span class="inline-block text-sm text-gray-600 font-semibold ml-auto">
            1000 tähemärki
          </span>
      </div>

      <textarea
        v-model="book.description"
        class="w-full h-[5.4375rem] border rounded-b border-default pl-3 pr-4 py-2.5 text-base"
        maxlength="1000"
        rows="5"
        @blur="validationErrors.description = ''"
      ></textarea>
    </div>

    <div class="h-8 text-red-600">
      {{ validationErrors.description }}
    </div>

    <div class="flex justify-end">
      <button
        class="mt-6 text-white font-bold py-2 px-4 rounded"
        :class="validateError ? 'bg-gray-700 hover:bg-gray-800' : 'bg-blue-500 hover:bg-blue-700'"
        :disabled="validateError"
        @click="validate"
      >
        Salvesta
      </button>
    </div>
  </div>
</template>