<template>
    <div class="container">
        <h2 id="formTitle">登录</h2>
        <form id="loginForm">
            <div class="form-group">
                <label for="loginUsername">用户名:</label>
                <input type="text" id="loginUsername" name="username" required />
            </div>
            <div class="form-group">
                <label for="loginPassword">密码:</label>
                <input type="password" id="loginPassword" name="password" required />
            </div>
            <div class="form-group">
                <button type="submit">登录</button>
            </div>
            <div class="toggle-link">
                <p id="toggleText">还没有账号？<a @click="toRegisterForm" id="toggleForm">注册</a></p>
            </div>
        </form>
        <form @invalid="loadPage" id="registerForm" style="display: none">
            <div class="form-group">
                <label for="registerUsername">用户名:</label>
                <input type="text" id="registerUsername" name="username" required />
            </div>
            <div class="form-group">
                <label for="registerEmail">邮箱:</label>
                <input type="email" id="registerEmail" name="email" required />
            </div>
            <div class="form-group">
                <label for="registerPassword">密码:</label>
                <input type="password" id="registerPassword" name="password" required />
            </div>
            <div class="form-group">
                <button @click="register" type="submit">注册</button>
            </div>
            <div class="toggle-link">
                <p id="toggleText">已经有账号？<a @click="loadPage" id="toggleForm">登陆</a></p>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: 'LoginComponent',
    setup() {
        const toRegisterForm = () => {
            const regFrom = document.getElementById('registerForm');
            const loginFrom = document.getElementById('loginForm');
            const formTitle = document.getElementById('formTitle');

            regFrom.style.display = 'block';
            loginFrom.style.display = 'none';
            formTitle.innerHTML = '注册';
        }
        const loadPage = () => {
            const regFrom = document.getElementById('registerForm');
            const loginFrom = document.getElementById('loginForm');
            const formTitle = document.getElementById('formTitle');

            regFrom.style.display = 'none';
            loginFrom.style.display = 'block';
            formTitle.innerHTML = '登录';
        }
        const register = () => {
            const regEmail = document.getElementById('registerEmail');
            const regUsername = document.getElementById('registerUsername');
            const regPassword = document.getElementById('registerPassword');

            if (regEmail.value === '' || regUsername.value === '' || regPassword.value === '') {
               alert('请填写完整的注册信息');
            }

            //下面写注册
        }
        return {
            toRegisterForm, loadPage , register
        }
    }
};
</script>

<style>
template {
    background-color: #f6f8fa;
    font-family: Arial, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    max-width: 400px;
    padding: 30px;
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    flex-direction: column;
    justify-content: center;
    align-items: center;
    display: grid;
}

.form-group {
    margin-bottom: 20px;
    width: 100%;
}

.form-group label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

.form-group input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.form-group button {
    width: 100%;
    padding: 10px;
    background-color: #0366d6;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.form-group button:hover {
    background-color: #0356c8;
}

.toggle-link {
    text-align: center;
    margin-top: 20px;
}

.toggle-link a {
    color: #0366d6;
    text-decoration: none;
}

.toggle-link a:hover {
    text-decoration: underline;
}
</style>