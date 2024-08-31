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
        <p><strong>昵称：</strong> <a v-if="!editInfoMode">{{ user.nickname }}</a> <input v-model="user.nickname"
            v-if="editInfoMode" /></p>
        <p><strong>账号：</strong> <a>{{ user.username }}</a></p>
        <p><strong>邮箱：</strong> <a>{{ user.email }}</a></p>
        <p><strong>个人签名：</strong> <a v-if="!editInfoMode">{{ user.signature }}</a> <input v-model="user.signature"
            v-if="editInfoMode" /></p>
        <p class="gray-text"><strong>ID:</strong> <a>{{ user.id }}</a></p>
        <p class="gray-text"><strong>注册时间：</strong> <a>{{ user.create_time }}</a></p>
        <button class="edit-info-button" @click="editInfo" v-if="!editInfoMode">编辑
          <i class="fas fa-pencil-alt"></i>
        </button>
        <button v-if="editInfoMode" @click="updateInfo">保存</button>
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
    
    if(user.nickname === null){
      nickname = '存续院研究院',user.id;
    }

    onMounted(async () => {
      if (store.state.isLoggedIn) {
        await store.dispatch('fetchUserData');
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

<style scoped>
.container {
  padding: 20px;
  max-width: 80%;
  margin: 1% auto;
  shape-rendering: auto;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  background-color: #f9f9f9;
}

.user-info-section {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  margin-left: auto;
  position: relative;
}

.avatar-container {
  position: relative;
  margin-right: 20px;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  position: absolute;
  top: -50px;
  left: 0;
  z-index: 1;
}

.edit-avatar-button {
  position: absolute;
  bottom: 0;
  right: 0;
  background: white;
  color: black;
  border: 1px solid black;
  z-index: 7;
  padding: 5px;
  cursor: pointer;
}

.user-details {
  flex: 1;
  position: relative;
  margin-left: 100px;
}

.user-details p {
  margin: 5px 0;
}

.gray-text {
  color: gray;
}

.edit-info-button {
  position: absolute;
  top: 0;
  right: 0;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 50%;
  padding: 5px;
  cursor: pointer;
}

.my-articles-section {
  margin-top: 20px;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.write-article-button {
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
}

.articles-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.article-box {
  flex: 1 1 calc(33.333% - 10px);
  background: #f9f9f9;
  padding: 10px;
  border: 1px solid #e0e0e0;
  border-radius: 5px;
}

.more-button {
  display: block;
  margin: 20px auto 0;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
}
</style>