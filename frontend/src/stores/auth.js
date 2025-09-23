import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', () => {

    const userId = ref(null)
    const nickname = ref('');
    const isLoggedIn = ref(false);

    const loginSuccess = (name, id) => {
        isLoggedIn.value = true;
        nickname.value = name;
        userId.value = id;
    }

    const logoutSuccess = () => {
        isLoggedIn.value = false;
        nickname.value = ''
        userId.value = null;
    }


    return { nickname, isLoggedIn, userId, loginSuccess, logoutSuccess }

})