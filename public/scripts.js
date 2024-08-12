document.getElementById('postForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    const postSection = document.querySelector('.posts');
    const newPost = document.createElement('article');

    const postTitle = document.createElement('h2');
    postTitle.textContent = title;

    const postContent = document.createElement('p');
    postContent.textContent = content;

    newPost.appendChild(postTitle);
    newPost.appendChild(postContent);

    postSection.appendChild(newPost);

    document.addEventListener('DOMContentLoaded', function() {
        // 模拟检查用户是否已登录
        const isLoggedIn = checkUserLoggedIn();

        if (isLoggedIn) {
            // 隐藏登录和注册按钮
            document.getElementById('loginLink').style.display = 'none';
            document.getElementById('registerLink').style.display = 'none';

            // 显示用户头像
            const userAvatar = document.getElementById('userAvatar');
            userAvatar.style.display = 'block';

            // 设置用户头像（可以从服务器获取用户头像 URL）
            userAvatar.querySelector('img').src = 'user-avatar.png'; // 替换为实际的用户头像 URL
        }
    });

    function checkUserLoggedIn() {
        // 这里可以添加实际的登录状态检查逻辑，例如检查 cookie 或 localStorage
        // 目前模拟用户已登录
        return true; // 修改为实际的登录状态
    }

    // 清空表单
    document.getElementById('postForm').reset();
});