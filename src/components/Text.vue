<template>
    <div class="text_container">
        <ul>
            <li v-for="(item, index) in text_list" :key="index">
                <a>{{item['Text_title']}}</a>
                <a>{{item['Text_content']}}</a>
                <a>{{item['username']}}</a>
            </li>
        </ul>
    </div>
    <div>
        <a>内容</a>
        <input v-model="text_content" type="text" />
        <a>作者</a>
        <input v-model="name" type="text" />
        <a>标题</a>
        <input v-model="text_title" type="text" />
        <button @click="addText">提交</button>
    </div>
</template>

<script>
import { onMounted, ref } from "vue"
import { request_text_list, request_add_text } from "../assets/script/request.js"
export default {
    name: 'textPage',
    setup() {
        const text_list = ref([]);
        const text_content = ref('');
        const text_title = ref('');
        const name = ref('');

        onMounted(async () => {
            console.log("textPage");
            const res = await request_text_list()
            text_list.value = res['data']['data'];
            console.log(res['data'])
        });
        const addText = async () => {
            const res = await request_add_text(text_content.value, text_title.value, name.value)
            console.log(res['data']['message'])
        }
        return {
            text_list, text_content, text_title, name, addText
        };
    }
};
</script>

<style scoped scr="@/assets/css/text.css"></style>