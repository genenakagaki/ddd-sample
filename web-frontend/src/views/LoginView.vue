<script lang="ts">
import { ref, computed } from 'vue'
import * as api from '@/lib/api'

export default {
    setup() {
        const username = ref('')
        const password = ref('')
        const errorMessage = ref('')
        const login = async () => {
            errorMessage.value = ''
            const response = await api.login(username.value, password.value)

            if (!response.ok && response.status === 401) {
                errorMessage.value = "Username or password is incorrect."
            }
        }

        return {
            username,
            password,
            login,
            errorMessage
        }
    }
}
</script>

<template>
    <v-form fast-fail @submit.prevent>
        <v-alert v-show="errorMessage.length > 0" :text="errorMessage" type="error" variant="tonal"></v-alert>
        <v-text-field v-model="username" label="Username"></v-text-field>

        <v-text-field v-model="password" label="Password"></v-text-field>

        <v-btn class="mt-2" type="submit" block @click="login">Login</v-btn>
    </v-form>
</template>
