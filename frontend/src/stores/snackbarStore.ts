import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useSnackbarStore = defineStore('snackbar', () => {
  const genericSnackbar = ref<genericSnackbarType>({state: false, message: "", color: "green"})

  function setErrorSnackbar(errorMessage: string) {
    genericSnackbar.value = {state: false, message: errorMessage, color: "red"}
  }

  function setSuccessSnackbar(errorMessage: string) {
    genericSnackbar.value = {state: false, message: errorMessage, color: "green"}
  }

  type genericSnackbarType = {
    state: boolean,
    message: string,
    color: string
  }

  return {
    genericSnackbar,
    setErrorSnackbar,
    setSuccessSnackbar
  }
})
