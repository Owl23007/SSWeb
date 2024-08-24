<template>
  <div id="app">
    <div>
      <header class="header_style">
        <div class="nav_logo">
          <nav>
            <a href="#" class="logo">
              <router-link to="/"><img src="@/assets/logo.png" alt="logo" style="height: 40px;"></router-link>
            </a>
          </nav>
        </div>
        <div class="nav_title">
          <span>这里是标题</span>
        </div>
        <div class="nav_menu">
          <ul>
            <li>
              <div class="header_button">
                <router-link to="/">主页</router-link>
              </div>
            </li>
            <li>
              <div class="header_button">
                <router-link to="/">资源</router-link>
              </div>
            </li>
            <li>
              <div class="header_button">
                <router-link to="/article">文章</router-link>
              </div>
            </li>
            <li>
            <div class="header_button">
              <router-link v-if="!isLoggedIn" to="/login">登录</router-link>
              <div v-else class="dropdown">
                <router-link to="/userinfo">个人主页</router-link>
                <div class="dropdown-content">
                  <a href="#" @click="logout">退出登录</a>
                </div>
              </div>
            </div>
          </li>      
            <li>
              <img src="@/assets/default-avatar.png" alt="用户头像" style="height: 40px; border-radius: 50%;">
            </li>
          </ul>
        </div>
      </header>
    </div>
    <div class="main_container">
      <router-view class="main_router_page"></router-view>
    </div>
  </div>
</template>

<script>
import { onMounted } from 'vue';
export default {
  name: 'App',
  setup() {

    // 在载入页面时调用
    onMounted(async () => {
    })
    return {
    };
  }
};
</script>

<style scoped>
.main_container {
  /* 里面的元素居中显示 */
  margin: 0 auto;
  max-width: 960px;
  margin-top: 16px;
}

.main_router_page {
  margin-top: 16px;
  /* 里面的元素居中显示 */
  margin: 0 auto;
}

/* 导航栏样式 */
.header_style {
  background-color: #24292e;
  padding: 16px;
  display: flex;
  height: 40px;
}

.nav_title {
  /* 文字居中 */
  width: 60%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.nav_title span {
  color: #ffffff;
  font-size: 20px;
}

.header_button {
  color: #ffffff;
  padding: 8px 16px;
  border-radius: 4px;
}

.header_button a {
  text-decoration: none;
}

.header_button:hover {
  background-color: #4a7494;
}

.nav_logo {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.nav_logo .logo {
  color: #ffffff;
  font-size: 24px;
  font-weight: bold;
}

.nav_menu {
  display: flex;
  justify-content: flex-end;
  flex: 1;
}

.nav_menu ul {
  list-style: none;
  display: flex;
  margin: 0;
  padding: 0;
}

.nav_menu ul li {
  margin-left: 20px;
}

.nav_menu ul li a {
  color: #ffffff;
  font-size: 16px;
}

/* 折叠菜单样式 */
.dropdown {
  position: relative;
  display: inline-block;
  margin-right: 60px;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #24292e;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: ffffff;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #4a7494;
}

.dropdown:hover .dropdown-content {
  display: block;
}
</style>