```vue
<template>
    <div>
        <header>
            <nav>
                <a href="#" class="logo">
                    <img src="@/assets/logo.png" alt="logo" style="height: 40px;">
                </a>
                <span class="nav-title">Oii的存续院</span>
                <ul>
                    <li><a href="#">主页</a></li>
                    <li><a href="#">资源</a></li>
                    <li><a href="#">文章</a></li>
                    <li><a href="login.html" id="loginLink">登录</a></li>
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
        </main>
    </div>
</template>

<script>
// eslint-disable-next-line
export default {
    name: 'Home', // eslint-disable-line vue/multi-word-component-names
    data() {
        return {
            // 你可以在这里添加组件的数据
        };
    },
    methods: {
        // 你可以在这里添加组件的方法
    }
};
</script>

<style scoped>
/* 你可以在这里添加组件的样式 */
nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    background-color: #f8f9fa;
}

ul {
    list-style-type: none;
    display: flex;
    gap: 15px;
}

li {
    display: inline;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown:hover .dropdown-content {
    display: block;
}

.hero {
    text-align: center;
    padding: 50px;
}
</style>