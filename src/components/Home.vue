<template>
    <div>
        <header>
            <nav>
                <a href="#" class="logo">
                    <img src="@/assets/logo.png" alt="logo" style="height: 40px;">
                </a>
                <span class="nav-title">主页|Oii的存续院</span>
                <ul>
                    <li><a href="#">主页</a></li>
                    <li><a href="#">资源</a></li>
                    <li><a href="#">文章</a></li>
                    <li><router-link to="/login">登录</router-link></li>
                    <li class="dropdown">
                        <a href="#" class="dropbtn">更多</a>
                        <div class="dropdown-content">
                            <a href="#">链接 1</a>
                            <a href="#">链接 2</a>
                            <a href="#">链接 3</a>
                            <a href="#">联系我们</a>
                            <a href="#">其他</a>
                        </div>
                    </li>
                    <li id="userAvatar" style="display: none;">
                        <img src="@/assets/default-avatar.png" alt="用户头像" style="height: 40px; border-radius: 50%;">
                    </li>
                </ul>
            </nav>
        </header>
        <main>
            <section class="hero">
                <h1>欢迎来到我的博客</h1>
                <p>分享我的编程旅程和技术见解。</p>
            </section>
            <section class="posts">
                <!-- 这里可以动态添加文章 -->
            </section>
        </main>
    </div>
</template>

<script>
export default {
    name: 'HomePage',
};
</script>

<style scoped>
/* 基本样式 */
body {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji";
    background-color: #f6f8fa;
    color: #24292e;
    margin: 0;
    padding: 0;
    line-height: 1.5;
}

a {
    color: #0366d6;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

/* 导航栏样式 */
header {
    background-color: #24292e;
    padding: 16px;
}

nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

nav .logo {
    color: #ffffff;
    font-size: 24px;
    font-weight: bold;
}

nav ul {
    list-style: none;
    display: flex;
    margin: 0;
    padding: 0;
}

nav ul li {
    margin-left: 20px;
}

nav ul li a {
    color: #ffffff;
    font-size: 16px;
}

/* 主要内容样式 */
main {
    padding: 20px;
}

.hero {
    background-color: #0366d6;
    color: #ffffff;
    padding: 40px;
    text-align: center;
}

.hero h1 {
    margin: 0;
    font-size: 36px;
}

.hero p {
    font-size: 20px;
}

/* 博客文章样式 */
.posts {
    margin-top: 20px;
}

.posts article {
    background-color: #ffffff;
    border: 1px solid #e1e4e8;
    border-radius: 6px;
    padding: 20px;
    margin-bottom: 20px;
}

.posts h2 {
    margin-top: 0;
    font-size: 24px;
}

.posts p {
    font-size: 16px;
    color: #586069;
}

.posts button {
    background-color: #28a745;
    color: #ffffff;
    border: none;
    padding: 10px 20px;
    border-radius: 6px;
    cursor: pointer;
}

.posts button:hover {
    background-color: #218838;
}

/* 折叠菜单样式 */
.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {
    background-color: #f1f1f1;
}

.dropdown:hover .dropdown-content {
    display: block;
}
</style>