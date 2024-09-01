<template>
  <div id="app">
    <div>
      <header class="header_style">
        <div class="nav_left">
          <div class="nav_logo_title">
            <nav>
              <a href="#" class="logo">
                <router-link to="/" class="no-underline"><img src="@/assets/logo.png" alt="logo"
                    style="height: 40px;"></router-link>
              </a>
            </nav>
            <span class="nav_title">存续院</span>
          </div>
          <div class="nav_links">
            <div class="header_button">
              <router-link to="/" class="no-underline">主页</router-link>
            </div>
            <div class="header_button">
              <router-link to="/resources" class="no-underline">资源</router-link>
            </div>
            <div class="header_button">
              <router-link to="/article" class="no-underline">文章</router-link>
            </div>
          </div>
        </div>
        <div class="nav_menu">
          <ul>
            <li class="nav_search">
              <input type="text" placeholder="世界在你脚下..." class="search_input">
              <button class="search_button">搜索</button>
            </li>
          </ul>
        </div>
        <div class="nav_right">
          <div v-if="!isLoggedIn" class="header_button">
            <router-link to="/login" class="no-underline">登录</router-link>
          </div>
          <div v-else class="avatar_button">
            <div class="dropdown">
              <img :src="user.userPic || defaultAvatar" alt="用户头像" class="avatar">
              <div class="dropdown-content">
                <router-link to="/userinfo" class="no-underline">个人主页</router-link>
                <a href="#" @click="logout">退出登录</a>
              </div>
            </div>
          </div>
        </div>
      </header>
    </div>
    <div class="main_container">
      <router-view class="main_router_page"></router-view>
    </div>
  </div>
</template>

<script>
import { computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  name: 'App',

  setup() {
    const store = useStore();
    const user = computed(() => store.state.user);
    const router = useRouter();
    const isLoggedIn = computed(() => store.state.isLoggedIn);

    const logout = () => {
      store.dispatch('logout');
      router.push('/');
    };

    // 在载入页面时调用
    onMounted(async () => {
      if (isLoggedIn.value) {
        await store.dispatch('fetchUserData');
      } else {
        router.push('/login');
      }
    });

    return {
      isLoggedIn,
      logout,
      user
    };
  }

};
</script>

<style>
body {
  background-color: #f0f0f0;
  /* 设置整个页面的背景颜色为偏灰的白色 */
  font-family: 'Roboto', sans-serif;
  /* 设置全局字体 */
  font-size: 16px;
  /* 设置全局字体大小 */
  line-height: 1.6;
  /* 设置全局行高 */
}

.header_style {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 5px;
  background-color: #f8f9fa;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  /* 添加下阴影 */
  border-radius: 10px;
  /* 添加圆角 */
  height: 50px;
}

.nav_left {
  display: flex;
  align-items: center;
}

.nav_logo_title {
  display: flex;
  align-items: center;
}

.nav_title {
  margin-left: 10px;
  /* 将标题与 logo 分开 */
  font-size: 24px;
  /* 设置标题字体大小 */
  font-weight: bold;
  /* 设置标题字体加粗 */
}

.nav_links {
  display: flex;
  align-items: center;
  margin-left: 20px;
  /* 将导航链接与标题分开 */
}

.header_button {
  padding: 10px 20px;
  font-size: 16px;
  /* 调整导航链接的大小 */
  text-decoration: none;
  /* 移除下划线 */
  color: #333;
  border: 1px solid transparent;
  border-radius: 4px;
  transition: background-color 0.3s, border-color 0.3s;
}

.header_button:hover {
  background-color: #e0e0e0;
}

.header_button.active {
  border-color: #007bff;
  background-color: #007bff;
  color: white;
}

.nav_menu {
  display: flex;
  align-items: center;
  justify-content: center;
  /* 将搜索框和按钮居中对齐 */
  flex-grow: 1;
  /* 使导航菜单占据剩余空间 */
  height: 45px;
}

.nav_menu ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
}

.nav_search {
  display: flex;
  align-items: center;
  height: 45px;
}

.search_input {
  border: 1px solid #ccc;
  border-right: none;
  border-radius: 10px 0 0 10px;
  width: 300px;
  /* 设置搜索输入框的宽度 */
  outline: none;
  /* 移除输入框的轮廓线 */
  font-size: 16px;
  /* 设置搜索输入框的字体大小 */
  height: 45px;
}

.search_button {
  border: 1px solid #ccc;
  border-left: none;
  border-radius: 0 10px 10px 0;
  background-color: #007bff;
  width: 60px;
  /* 设置搜索按钮的宽度 */
  color: white;
  cursor: pointer;
  font-size: 16px;
  /* 设置搜索按钮的字体大小 */
  height: 49px;
}

.search_button:hover {
  background-color: #0056b3;
}

.nav_right {
  display: flex;
  align-items: center;
  margin-left: auto;
  /* 将 .nav_right 元素右对齐 */
  margin-right: 0;
}

.avatar_button {
  margin-top: 5px;
}

.avatar {
  padding-right: 5%;
  height: 55px;
  /* 设置头像的高度 */
  width: 55px;
  /* 设置头像的宽度 */
  border-radius: 50%;
  /* 将头像设置为圆形 */
  cursor: pointer;
  /* 设置鼠标悬停时的样式为手型 */
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
  border-radius: 4px;
  margin-top: 0;
  right: 0;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  font-size: 16px;
  /* 设置下拉菜单项的字体大小 */
}

.dropdown-content a:hover {
  background-color: #f1f1f1;
}

.dropdown:hover .dropdown-content {
  display: block;
}

/* 新增的样式 */
.no-underline {
  text-decoration: none;
  /* 移除下划线 */
  color: inherit;
  /* 保持原有颜色 */
}
</style>