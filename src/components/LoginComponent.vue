<template>
    <div class="login_container">
        <div class="login_form">
            <h2>登陆账号</h2>
            <div class="form-group">
                <label for="loginUsername">用户名:</label>
                <input type="text" v-model="username" required />
            </div>
            <div v-show="!islogin" class="form-group">
                <label for="registerEmail">邮箱:</label>
                <input type="email" v-model="email" required />
            </div>
            <div class="form-group">
                <label for="loginPassword">密码:</label>
                <input type="password" v-model="password" required />
            </div>
            <div class="form-group">
                <button @click="reg_or_login" type="submit" v-text="button_text"></button>
            </div>
            <div class="toggle-link">
                <p><a v-text="text_info_prefix"></a><a @click="toreg_or_login" class="login_text_button">{{ text_info
                        }}</a></p>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { ref } from 'vue';

export default {
    name: 'LoginComponent',
    setup() {
        const username = ref('');
        const password = ref('');
        const email = ref('');
        const button_text = ref('登陆')
        const islogin = ref(true);
        const text_info_prefix = ref('还没有账号？');
        const text_info = ref('注册');
        const toreg_or_login = () => {
            if (islogin.value) {
                //相当于点了蓝色注册小字
                islogin.value = false;
                text_info_prefix.value = '已有账号？';
                text_info.value = '登陆';
                button_text.value = '注册';
                username.value = "";
                password.value = "";
                email.value = "";
                return;
            }
            //相当于点击了蓝色登陆小字
            islogin.value = true;
            text_info_prefix.value = '还没有账号？';
            text_info.value = '注册';
            button_text.value = '登陆';
        }
        const register_request = async (username, password, email) => {
            const res = await axios({
                url: 'http://localhost:8080/api/register',//请求地址
                method: 'post',//请求方式
                data: {//请求参数
                    username: username,
                    password: password,
                    email: email
                },
            })
            return res;
        }
        const login_request = async (username, password) => {
            const res = await axios({
                url: 'http://localhost:8080/api/login',//请求地址
                method: 'post',//请求方式
                data: {//请求参数
                    username: username,
                    password: password
                },
            })
            return res;
        }
        const reg_or_login = async () => {
            if (islogin.value) {
                if (username.value == '' || password.value == '') {
                    console.log("输入不能为空。");
                    return;
                }
                //下面写登录
                const res = await login_request(username.value, password.value);
                //后端回复的消息
                if (res['data']['status'] == 'success') {
                    console.log("登录成功。");
                    //导航到主页
                    window.location.href = "/";
                } else {
                    console.log(res['data']['message']);
                }
                return;
            }
            if (email.value == '' || username.value == '' || password.value == '') {
                console.log("输入不能为空。");
                return;
            }

            //下面写注册(未加密)
            const res = await register_request(username.value, password.value, email.value);

            //后端回复的消息
            if (res['data']['status'] == 'success') {
                username.value = "";
                password.value = "";
                email.value = "";
                islogin.value = true;
                text_info_prefix.value = '还没有账号？';
                text_info.value = '注册';
                button_text.value = '登陆';
                console.log("注册成功。");
            }
            else
                console.log(res['data']['message']);
        }
        return {
            reg_or_login, toreg_or_login, text_info_prefix, text_info, button_text, islogin, password, email, username
        }
    }
};
</script>

<style>
.login_container {
    max-width: 40%;
    height: 100px;
    margin: 0 auto;
    text-align: center;
    /* 顶格 */
    margin-top: 100px;
}

.login_form {
    background-color: #fffdfd;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
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
    width: 95%;
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

.login_text_button {
    color: #0366d6;
    text-decoration: none;
}

.login_text_button:hover {
    text-decoration: underline;
}
</style>