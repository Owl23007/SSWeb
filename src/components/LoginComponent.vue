<template>
    <div class="login_container">
        <div class="login_form">
            <div class="title_text">
                <a>{{ button_text }}页面</a>
            </div>
            <div class="form-group">
                <label for="loginUsername">用户名:</label>
                <div class="input_box">
                    <input type="text" v-model="username" required />
                </div>
            </div>
            <div v-show="!islogin" class="form-group">
                <label for="registerEmail">邮箱:</label>
                <div class="input_box">
                    <input type="email" v-model="email" required />
                </div>
            </div>
            <div class="form-group">
                <label for="loginPassword">密码:</label>
                <div class="input_box">
                    <input type="password" v-model="password" required />
                </div>
            </div>
            <div class="form-group">
                <button @click="reg_or_login" type="submit" v-text="button_text"></button>
            </div>
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
import { login_request, register_request } from '@/assets/script/request';

export default {
    name: 'LoginComponent',
    setup() {
        const username = ref('');
        const password = ref('');
        const email = ref('');
        const button_text = ref('登陆');
        const text_info_prefix = ref('还没有账号？');
        const text_info = ref('注册');
        const islogin = ref(true);

        const toreg_or_login = () => {
            if (islogin.value) {
                islogin.value = false;
                text_info_prefix.value = '已有账号？';
                text_info.value = '登陆';
                button_text.value = '注册';
                username.value = "";
                password.value = "";
                email.value = "";
                return;
            }
            islogin.value = true;
            text_info_prefix.value = '还没有账号？';
            text_info.value = '注册';
            button_text.value = '登陆';
        }

        const reg_or_login = async () => {
            if (islogin.value) {
                if (username.value === '' || password.value === '') {
                    console.log("输入不能为空。");
                    return;
                }
                try {
                    const res = await login_request({
                        username: username.value,
                        password: password.value
                    });
                    if (res.status === 'success') {
                        console.log("登录成功。");
                        window.location.href = "/home";
                    } else {
                        alert(res.message);
                        console.log(res.message);
                    }
                } catch (e) {
                    alert("登录失败。");
                    console.log(e);
                }
            } else {
                if (email.value === '' || username.value === '' || password.value === '') {
                    console.log("输入不能为空。");
                    return;
                }
                try {
                    const res = await register_request({
                        username: username.value,
                        password: password.value,
                        email: email.value
                    });
                    if (res.status === 'success') {
                        username.value = "";
                        password.value = "";
                        email.value = "";
                        islogin.value = true;
                        text_info_prefix.value = '还没有账号？';
                        text_info.value = '注册';
                        button_text.value = '登陆';
                        alert(res.message);
                    } else {
                        console.log(res.message);
                    }
                } catch (e) {
                    alert("注册失败。");
                    console.log(e);
                }
            }
        }

        return {
            reg_or_login, toreg_or_login, text_info_prefix, text_info, button_text, islogin, password, email, username
        }
    }
};
</script>

<style scoped src="@/assets/css/loginpage.css"></style>