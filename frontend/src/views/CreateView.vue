<script setup lang="ts">

</script>

<template>
  <div class="p-4">
    <div>
      <div class="flex justify-between">
        <label class="block text-lg font-medium">
          Raamatu nimi
        </label>

        <span class="inline-block text-sm text-gray-600 font-semibold ml-auto">100 tähemärki</span>
      </div>

      <input
        class="mt-2 w-full h-[2.75rem] border rounded border-default pl-3 pr-4 py-2.5 text-base"
        maxlength="100"
      >
    </div>

    <div class="mt-6">
      <div class="flex justify-between">
        <label class="block text-lg font-medium">
          Autor
        </label>

        <span class="inline-block text-sm text-gray-600 font-semibold ml-auto">100 tähemärki</span>
      </div>

      <input
        class="mt-2 w-full h-[2.75rem] border rounded border-default pl-3 pr-4 py-2.5 text-base"
        maxlength="100"
      >
    </div>

    <div class="mt-6">
      <div class="flex justify-between">
        <label class="block text-lg font-medium">
          Avaldamise aasta
        </label>
      </div>

      <input
        class="mt-2 w-full h-[2.75rem] border rounded border-default pl-3 pr-4 py-2.5 text-base"
        type="text" pattern="[0-9]{4}" placeholder="YYYY" maxlength="4" required
      >
    </div>

    <div class="flex mt-6">
      <input
        type="checkbox"
        id="available"
        class="h-6 w-6 mr-2"
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
          Autor
        </label>

        <span class="inline-block text-sm text-gray-600 font-semibold ml-auto">1000 tähemärki</span>
      </div>

      <textarea
        class="w-full h-[5.4375rem] border rounded-b border-default pl-3 pr-4 py-2.5 text-base"
        maxlength="120"
        rows="5"
      ></textarea>
    </div>

    <div class="flex justify-end">
      <button class="mt-6 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
        Loo
      </button>
    </div>


  </div>
</template>

<style scoped>

</style>