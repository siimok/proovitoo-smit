<template>
  <header>
    <nav class="bg-sky-500 h-14 text-center font-semibold text-xl pt-3">
      <router-link class="m-4" to="/">Pealeht</router-link>
      <router-link class="m-4" to="/create">Loo uus</router-link>
    </nav>
  </header>

  <RouterView />
</template>