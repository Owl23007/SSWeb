<template>
  <div class="article-list">
    <div v-if="articles.length === 0" class="empty-state">
      <p>暂无文章，请稍后再试。</p>
    </div>

    <div v-else>
      <div v-for="article in articles" :key="article.id" class="article-item">

        <img :src="article.coverImg" alt="Article Image" class="article-image" />
        <div class="article-content">
          <h2>{{ article.title }}</h2>
          <p>{{ article.categoryId }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { PreviewArticle } from '@/assets/script/pojo/article.js';
import { getArticles_get } from '@/assets/script/article_request.js';
import { useStore } from 'vuex';
import { ref ,onMounted } from 'vue';
export default {
  name: 'ArticleList',
  setup() {
    const store = useStore();
    const articles = ref([]);

    onMounted(async () => {
      const res = await getArticles_get(store.state.token, 1, 2);
      if (res.code === 0) {
        articles.value = res.data.map(item => new PreviewArticle(item.id, item.title, item.coverImg, item.categoryId, item.createUser, item.createTime));
      }
    });
    return {
      articles
    }
  }
};
</script>
<style scoped src="@/assets/css/articlelist.css"></style>