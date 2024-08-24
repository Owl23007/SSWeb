<template>
    <div class="login_container">
        <div class="login_form">
            <div class="title_text">
                <a>{{ button_text }}页面</a>
            </div>
            <form @submit.prevent="reg_or_login">
                <div class="form-group">
                    <label for="loginUsername">用户名:</label>
                    <div class="input_box">
                        <input type="text" v-model="username" name="username" autocomplete="username" required />
                    </div>
                </div>
                <div v-show="!islogin" class="form-group">
                    <label for="registerEmail">邮箱:</label>
                    <div class="input_box">
                        <input type="email" v-model="email" name="email" autocomplete="email" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="loginPassword">密码:</label>
                    <div class="input_box">
                        <input type="password" v-model="password" name="password" autocomplete="current-password"
                            required />
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" v-text="button_text"></button>
                </div>
            </form>
            <div class="toggle-link">
                <p>
                    <a>{{ text_info_prefix }}</a>
                    <a @click="toreg_or_login" class="login_text_button">{{ text_info }}</a>
                </p>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';
import store from '../store/index.js';
import router from '../router/index.js';
import { login_post, register_post } from '../assets/script/login_request.js';


export default {
    name: 'LoginComponent',
    setup() {
        // 存储输入框的内容
        const username = ref('');
        const password = ref('');
        const email = ref('');
        // 存储页面信息
        const islogin = ref(true);
        const text_info_prefix = ref('还没有账号？');
        const text_info = ref('注册');
        const button_text = ref('登陆');

        // 切换注册和登录页面
        const toreg_or_login = () => {
            islogin.value = !islogin.value;
            if (islogin.value) {
                text_info_prefix.value = '还没有账号？';
                text_info.value = '注册';
                button_text.value = '登陆';
                return
            }
            text_info_prefix.value = '已有账号？';
            text_info.value = '登陆';
            button_text.value = '注册';
        };

        // 当点击登录或注册按钮后调用
        const reg_or_login = async () => {

            // 如果当前是登陆操作
            if (islogin.value) {
                try {
                    // 调用登录请求函数
                    const res = await login_post(username.value, password.value);

                    // 判断是否登录成功
                    if (res.code === 0) {
                        alert("登录成功！");
                        store.login(res.data);      // 存储 JWT 并更新登录状态
                        router.push('/home');       //跳转home
                        return;
                    }
                    // 登录失败
                    alert(res.message);

                } catch (e) {
                    console.log(e);
                    return;
                }
            }

            // 当前是注册操作
            // 调用注册请求函数
            try {
                const res = await register_post(username.value, password.value, email.value);
                // 注册成功
                if (res.code === 0) {
                    // 切换到登陆页面
                    toreg_or_login();
                    return;
                }
                // 注册失败
                alert(res.message);
            } catch (e) {
                console.log(e);
            }
        };

        return {
            reg_or_login,
            toreg_or_login,
            text_info_prefix,
            text_info,
            button_text,
            islogin,
            password,
            email,
            username,
        };
    }
};
</script>

<style scoped src="@/assets/css/loginpage.css"></style>