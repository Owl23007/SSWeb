<template>
    <div class="article">
        <div class="no_conetnt" v-if="article.content === null">
            暂无文章，请稍后再试。
        </div>
        <div class="content" v-else>
            {{ article.content }}
        </div>
    </div>

</template>

<script>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import { getArticleByID_get } from '../assets/script/article_request.js';

export default {
    name: 'ArticleInfo',
    setup() {
        const article = ref({});
        const store = useStore();

        onMounted(async () => {
            // 获取路由参数
            const id = window.location.href.split('/').pop();
            // 获取根据id获取文章详情
            const res = await getArticleByID_get(store.state.token, id)
            if (res.code === 0) {
                article.value = res.data
            }
        })
        return {
            article
        }
    }
}
</script>

<style scoped>
.article {
    padding-top: 70px;
}
</style>