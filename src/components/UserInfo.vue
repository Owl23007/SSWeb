<template>
  <div class="container">
    <!-- 板块一：用户信息 -->
    <div class="user-info-section">
      <div>
        <h2>个人卡片</h2>
        <UserCard :user="user" @update-avatar="updateAvatar" />
      </div>

      <div class="user-details">
        <h2>个人信息</h2>
        <p>
          <strong>昵称：</strong>
          <a v-if="!editInfoMode">{{ user.nickname }}</a>
          <input v-model="user.nickname" v-if="editInfoMode" />
        </p>
        <p><strong>账号：</strong> <a>{{ user.username }}</a></p>
        <p><strong>邮箱：</strong> <a>{{ user.email }}</a></p>
        <p>
          <strong>个人签名：</strong>
          <a v-if="!editInfoMode">{{ user.signature }}</a>
          <input v-model="user.signature" v-if="editInfoMode" />
        </p>
        <p class="gray-text"><strong>ID:</strong> <a>{{ user.id }}</a></p>
        <p class="gray-text"><strong>注册时间：</strong> <a>{{ user.create_time }}</a></p>
        <button class="edit-info-button" @click="editInfo" v-if="!editInfoMode">编辑
          <i class="fas fa-pencil-alt"></i>
        </button>
        <!-- 样式暂时用的 write-article-button -->
        <button class="write-article-button" v-if="editInfoMode" @click="updateInfo">保存</button>
        <button class="write-article-button" v-if="editInfoMode" @click="editInfo">取消</button>
      </div>
    </div>

    <!-- 板块二：我的投稿 -->
    <div class="my-articles-section">
      <div class="section-header">
        <h2>我的投稿</h2>
        <button class="write-article-button" @click="writeArticle">
          <i class="fas fa-pen"></i> 撰写
        </button>
      </div>
      <div class="articles-container">
        <div class="article-box" v-for="article in articles" :key="article.id">
          <h3>{{ article.title }}</h3>
          <p>{{ article.summary }}</p>
        </div>
      </div>
      <button class="more-button" @click="loadMoreArticles">更多</button>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import defaultAvatar from '@/assets/default-avatar.png';
import UserCard from './UserCard.vue';
import { updateUserInfo_put } from '../assets/script/user_request.js';

export default {
  name: 'UserInfo',
  components: {
    UserCard
  },
  setup() {
    const store = useStore();
    const user = computed(() => store.state.user);
    const articles = ref([]);
    const editInfoMode = ref(false);

    onMounted(async () => {
      if (store.state.isLoggedIn) {
        // 假设一个获取用户投稿的 action
        articles.value = await store.dispatch('fetchUserArticles');
      } else {

        //router.push('/login');
      }
    });

    const editAvatar = () => {
      // 编辑头像的逻辑
    };

    const editInfo = () => {
      // 编辑用户信息的逻辑
      editInfoMode.value = !editInfoMode.value;
    };

    const updateInfo = async () => {
      // 更新用户信息的逻辑
      editInfoMode.value = false;
      const res = await updateUserInfo_put(store.state.token, user.value.nickname, user.value.signature)
      if (res.code === 0) {
        await store.dispatch('fetchUserData');
        alert("更新成功！");
      } else {
        alert("更新失败！");
      }
    };

    const writeArticle = () => {
      // 撰写新文章的逻辑
    };

    const loadMoreArticles = () => {
      // 加载更多文章的逻辑
    };

    return {
      user,
      articles,
      editInfoMode,
      editAvatar,
      editInfo,
      writeArticle,
      loadMoreArticles,
      updateInfo,
      defaultAvatar,
    };
  }
};
</script>

<style scoped src="@/assets/css/userinfo.css"></style>