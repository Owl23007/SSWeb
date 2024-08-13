<template>
  <div id="app">
    <div>
      <header class="header_style">
        <div class="nav_logo">
          <nav>
            <a href="#" class="logo">
              <router-link to="/"><img src="@/assets/logo.png" alt="logo" style="height: 40px;"></router-link>
            </a>
            <span class="nav_title">这里是标题</span>
          </nav>
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
                <router-link to="/resource">资源</router-link>
              </div>
            </li>
            <li>
              <div class="header_button">
                <router-link to="/blog">文章</router-link>
              </div>
            </li>
            <li>
              <div class="header_button">
                <router-link to="/login">登录</router-link>
              </div>
            </li>
            <li class="dropdown">
              <div class="header_button">
                <a>更多</a>
              </div>
              <div class="dropdown-content">
                <router-link to="/callme">联系我们</router-link>
                <router-link to="/more">其他</router-link>
              </div>
            </li>
            <li style="display: none;">
              <img src="@/assets/default-avatar.png" alt="用户头像" style="height: 40px; border-radius: 50%;">
            </li>
          </ul>
        </div>
      </header>
    </div>
    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style>
.container {
  /* 居中 */
  margin: 0 auto;
  max-width: 960px;
  margin-top: 16px;
}

/* 导航栏样式 */
.header_style {
  background-color: #24292e;
  padding: 16px;
  display: flex;
  height: 40px;
}

.nav_title {
  color: #218838;
  font-size: 20px;
  margin-left: 10px;
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