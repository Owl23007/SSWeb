<template>
  <div class="article-list">
    <div v-if="articles.length === 0" class="empty-state">
      <p>暂无文章，请稍后再试。</p>
    </div>

    <div class="article-box" v-else v-for="article in articles" :key="article.id">
      <ArticlePerview :data="article" />
    </div>
  </div>
</template>

<script>
import { PreviewArticle } from '@/assets/script/pojo/article.js';
import { getArticles_get, getCategory_get } from '@/assets/script/article_request.js';
import { useStore } from 'vuex';
import { ref, onMounted } from 'vue';
import ArticlePerview from './ArticlePerview.vue';
export default {
  name: 'ArticleList',
  components: {
    ArticlePerview
  },
  setup() {
    const store = useStore();
    const articles = ref([]);

    onMounted(async () => {
      const res = await getArticles_get(store.state.token, 1, 5);
      if (res.code === 0) {
        articles.value = res.data.map(item => new PreviewArticle(item.id, item.title, item.coverImg, item.categoryId, item.createUser, item.createTime));
        for (let i = 0; i < articles.value.length; i++) {
          const res = await getCategory_get(store.state.token, articles.value[i].categoryId);
          if (res.code === 0) {
            articles.value[i].category = res.data;
          }
        }
      }
    });
    return {
      articles
    }
  }
};
</script>
<style scoped src="@/assets/css/articlelist.css"></style>