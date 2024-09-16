<script lang="ts">
import { ref, computed } from 'vue'
import { UserControllerApi } from '@/api/generated/apis/UserControllerApi'
import type { ErrorData } from '@/api/generated';

export default {
    setup() {
        const username = ref('')
        const password = ref('')
        const error = ref<ErrorData>({});

        const register = async () => {
            error.value = {};

            const userApi = new UserControllerApi();
            try {
                const res = await userApi.register({
                    registerUserBody: {
                        username: username.value,
                        password: password.value
                    }
                })
                console.log(res)
            } catch (e: any) {
                error.value = await e.response.json()
            }
        }

        return {
            username,
            password,
            register,
            error
        }
    }
}
</script>

<template>
    <v-form fast-fail @submit.prevent>
        <v-alert v-show="error.errorMessage" :text="error.errorMessage" type="error" variant="tonal"></v-alert>
        <v-text-field v-model="username" :error-messages="error?.fieldNameByErrorMessage?.username"
            label="Username"></v-text-field>

        <v-text-field v-model="password" :error-messages="error?.fieldNameByErrorMessage?.password"
            label="Password"></v-text-field>

        <v-btn class="mt-2" type="submit" block @click="register">Register</v-btn>
    </v-form>
</template>
