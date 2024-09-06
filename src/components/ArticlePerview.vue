<template>
    <div class="article_preview">
        <div class="info">
            <div class="coverimg">
                <img :src="data.coverImg" alt="Article Image" />
            </div>
            <div class="no_coverimg">
                <div class="article_preview_title">{{ data.title }}</div>
                <div class="article_preview_category">标签: {{ data.category }}</div>
                <div class="article_preview_createtime">发布时间: {{ data.createTime }}</div>
            </div>
        </div>
        <div class="button">
            <button class="function_button" @click="checkArticle(data.id)">查看</button>
            <button class="function_button" @click="deleteArticle(data.id)"
                v-if="data.createUser === store.state.user.id">删除</button>
        </div>
    </div>
</template>

<script>
import { PreviewArticle } from '@/assets/script/pojo/article.js';
import { deleteArticle_delete, getArticleByID_get } from '@/assets/script/article_request.js';
import { useStore } from 'vuex';
import router from '@/router';

export default {
    name: 'ArticlePerview',
    // 接收父组件传递的数据，类型为 PreviewArticle
    props: {
        data: {
            type: PreviewArticle,
            required: true
        }
    },
    setup() {
        const store = useStore();

        // 删除文章
        const deleteArticle = async (id) => {
            const res = await deleteArticle_delete(store.state.token, id);
            if (res.code === 0) {
                alert("删除成功！");
                window.location.reload();
            } else {
                alert("删除失败！");
            }
        }

        // 查看文章
        const checkArticle = async (id) => {
            const res = await getArticleByID_get(store.state.token, id);
            if (res.code === 0) {
                alert("获取文章成功！");
                // 路由到 文章详情页，传入文章id
                router.push({ name: 'ArticleInfo', params: { id: id } });
            } else {
                alert("获取文章失败！");
            }
        }

        return {
            store,
            deleteArticle,
            checkArticle
        }
    }
}
</script>

<style scoped src="@/assets/css/articleperview.css"></style>