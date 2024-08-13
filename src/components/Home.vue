<template>
    <div>
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
a {
    color: #0366d6;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
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
</style>