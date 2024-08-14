<template>
    <div class="text_container">
        <ul>
            
        </ul>
    </div>
</template>

<script>
import { onMounted, ref } from "vue"
import { request_text_list } from "../assets/script/request.js"
export default {
    name: 'textPage',
    setup() {
        const text_list = ref([]);

        onMounted(async () => {
            console.log("textPage");

            await request_text_list().then(res => {
                text_list.value = res.data;
            });
        });
        return {
            text_list
        };
    }
};
</script>

<style scoped scr="@/assets/css/text.css"></style>