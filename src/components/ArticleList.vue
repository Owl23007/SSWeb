<template>
  <div class="article-list">
    <div v-if="articles.length === 0" class="empty-state">
      <p>暂无文章，请稍后再试。</p>
    </div>
    <div v-else>
      <div v-for="article in articles" :key="article.id" class="article-item">
        <img :src="article.image" alt="Article Image" class="article-image" />
        <div class="article-content">
          <h2>{{ article.title }}</h2>
          <p>{{ article.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ArticleList',
  data() {
    return {
      articles: [] // 初始化为空数组
    };
  },
  mounted() {
    this.fetchArticles();
  },
  methods: {
    async fetchArticles() {
      try {
        const articles = await this.loadArticles();
        this.articles = articles;
      } catch (error) {
        console.error('Error loading articles:', error);
      }
    },
    loadArticles() {
      return new Promise((resolve) => {
        // 模拟异步数据加载
        setTimeout(() => {
          resolve([
            {
              id: 1,
              title: '示例文章标题 1',
              description: '这是文章 1 的描述。',
              image: 'https://via.placeholder.com/150'
            },
            {
              id: 2,
              title: '示例文章标题 2',
              description: '这是文章 2 的描述。',
              image: 'https://via.placeholder.com/150'
            }
          ]);
        }, 2000); // 模拟2秒延迟
      });
    }
  }
};
</script>
<style scoped src="@/assets/css/articlelist.css">
</style>