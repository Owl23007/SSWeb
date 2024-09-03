<template>
  <div class="container">
    <!-- 板块一：用户信息 -->
    <div class="user-info-section">
      <div class="usercard" @click="editCard">
        <h2>个人卡片</h2>
        <UserCard :user="store.state.user" @update-avatar="updateAvatar" />
      </div>
      <div style="margin-left: 15px" v-if="!cardSetMode">
        <div class="user-details">
          <h2>个人信息</h2>
          <p>
            <strong>昵称：</strong>
            <a v-if="!editInfoMode">{{ store.state.user.nickname }}</a>
            <input v-model="store.state.user.nickname" v-if="editInfoMode" />
          </p>
          <p>
            <strong>账号：</strong>
            <a>{{ store.state.user.username }}</a>
          </p>
          <p>
            <strong>邮箱：</strong>
            <a>{{ store.state.user.email }}</a>
          </p>
          <p>
            <strong>个人签名：</strong>
            <a v-if="!editInfoMode">{{ store.state.user.signature }}</a>
            <input v-model="store.state.user.signature" v-if="editInfoMode" />
          </p>
          <p class="gray-text">
            <strong>ID:</strong>
            <a>{{ store.state.user.id }}</a>
          </p>
          <p class="gray-text">
            <strong>注册时间：</strong>
            <a>{{ store.state.user.createTime }}</a>
          </p>


          <!-- 样式暂时用的 write-article-button -->
          <svg width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg"
            class="right-entry-icon">
            <path
              d="M15.435 17.7717H4.567C2.60143 17.7717 1 16.1723 1 14.2047V5.76702C1 3.80144 2.59942 2.20001 4.567 2.20001H15.433C17.3986 2.20001 19 3.79943 19 5.76702V14.2047C19.002 16.1703 17.4006 17.7717 15.435 17.7717ZM4.567 4.00062C3.59327 4.00062 2.8006 4.79328 2.8006 5.76702V14.2047C2.8006 15.1784 3.59327 15.9711 4.567 15.9711H15.433C16.4067 15.9711 17.1994 15.1784 17.1994 14.2047V5.76702C17.1994 4.79328 16.4067 4.00062 15.433 4.00062H4.567Z"
              fill="currentColor"></path>
            <path
              d="M9.99943 11.2C9.51188 11.2 9.02238 11.0667 8.59748 10.8019L8.5407 10.7635L4.3329 7.65675C3.95304 7.37731 3.88842 6.86226 4.18996 6.50976C4.48954 6.15544 5.0417 6.09699 5.4196 6.37643L9.59412 9.45943C9.84279 9.60189 10.1561 9.60189 10.4067 9.45943L14.5812 6.37643C14.9591 6.09699 15.5113 6.15544 15.8109 6.50976C16.1104 6.86409 16.0478 7.37731 15.6679 7.65675L11.4014 10.8019C10.9765 11.0667 10.487 11.2 9.99943 11.2Z"
              fill="currentColor"></path>
          </svg>
          <button class="write-article-button" v-if="editInfoMode" @click="updateInfo">保存</button>
          <button class="write-article-button" v-if="editInfoMode" @click="editInfo">取消</button>
        </div>
        <div class="edit-info-button" @click="editInfo" v-if="!editInfoMode">
          <svg aria-hidden="true" height="16" viewBox="0 0 16 16" version="1.1" width="16" data-view-component="true"
            class="octicon octicon-pencil">
            <path
              d="M11.013 1.427a1.75 1.75 0 0 1 2.474 0l1.086 1.086a1.75 1.75 0 0 1 0 2.474l-8.61 8.61c-.21.21-.47.364-.756.445l-3.251.93a.75.75 0 0 1-.927-.928l.929-3.25c.081-.286.235-.547.445-.758l8.61-8.61Zm.176 4.823L9.75 4.81l-6.286 6.287a.253.253 0 0 0-.064.108l-.558 1.953 1.953-.558a.253.253 0 0 0 .108-.064Zm1.238-3.763a.25.25 0 0 0-.354 0L10.811 3.75l1.439 1.44 1.263-1.263a.25.25 0 0 0 0-.354Z">
            </path>
          </svg>
          编辑
        </div>
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
  <div>
    <input type="text" placeholder="标题" v-model="title">
    <input type="text" placeholder="正文" v-model="content">
    <input type="text" placeholder="封面地址" v-model="coverImg">
    <select name="category" v-model="state">
      <option value="草稿">草稿</option>
      <option value="已发布">已发布</option>
    </select>
    <input type="number" placeholder="ID" v-model="categoryId">
    <button @click="addArticle">提交</button>
  </div>
  <div>
    <input type="text" placeholder="分类名" v-model="categoryName">
    <input type="text" placeholder="别名" v-model="categoryAlias">
    <button @click="addCategory">提交</button>
  </div>
  <div class="card-setting" v-if="store.state.CardSetting">
    <CardSetting />
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';
import defaultAvatar from '@/assets/default-avatar.png';
import UserCard from './UserCard.vue';
import { updateUserInfo_put } from '../assets/script/user_request.js';
import CardSetting from './CardSetting.vue';
import { addArticle_post, addCategory_post } from '../assets/script/article_request.js';

export default {
  name: 'UserInfo',
  components: {
    UserCard,
    CardSetting
  },
  setup() {
    const store = useStore();
    const articles = ref([]);
    const editInfoMode = ref(false);

    // 文章部分
    const title = ref('');
    const content = ref('');
    const coverImg = ref('');
    const state = ref('草稿');
    const categoryId = ref(1);

    // 分类部分
    const categoryName = ref('');
    const categoryAlias = ref('');

    onMounted(async () => {
      if (!store.state.user) {
        articles.value = await store.dispatch('fetchUserInfo');
      }
      if (store.state.isLoggedIn) {
        // 假设一个获取用户投稿的 action
        articles.value = await store.dispatch('fetchUserArticles');
      } else {

        //router.push('/login');
      }
    });

    const editCard = async () => {
      // 编辑卡片的逻辑
      await store.dispatch('setcartsettingmode', true);
    };

    const editInfo = () => {
      // 编辑用户信息的逻辑
      editInfoMode.value = !editInfoMode.value;
    };

    const updateInfo = async () => {
      // 更新用户信息的逻辑
      editInfoMode.value = false;
      const res = await updateUserInfo_put(store.state.token, store.state.user.nickname, store.state.user.signature)
      if (res.code === 0) {
        await store.dispatch('fetchUserData');
        alert("更新成功！");
      } else {
        alert("更新失败！");
      }
    };

    const addArticle = async () => {
      // 添加新文章的逻辑
      const res = await addArticle_post(store.state.token, title.value, content.value, coverImg.value, state.value, categoryId.value);
      if (res.code === 0) {
        alert("添加成功！");
      } else {
        alert("添加失败！");
      }
    };

    const addCategory = async () => {
      // 添加新文章分类的逻辑
      const res = await addCategory_post(store.state.token, categoryName.value, categoryAlias.value);
      if (res.code === 0) {
        alert("添加成功！");
      } else {
        alert("添加失败！");
      }
    };

    const writeArticle = () => {
      // 撰写新文章的逻辑
    };

    const loadMoreArticles = () => {
      // 加载更多文章的逻辑
    };

    return {
      store,
      articles,
      editInfoMode,
      editCard,
      editInfo,
      writeArticle,
      addArticle,
      addCategory,
      loadMoreArticles,
      updateInfo,
      defaultAvatar,
      title,
      content,
      coverImg,
      state,
      categoryId,
      categoryName,
      categoryAlias
    };
  }
};
</script>

<style scoped src="@/assets/css/userinfo.css"></style>