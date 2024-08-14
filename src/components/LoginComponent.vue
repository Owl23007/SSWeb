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
import { register_request, login_request } from "../assets/script/request.js";
import { login_status } from "../assets/script/loginstatus.js";

export default {
    name: 'LoginComponent',
    setup() {
        // 输入框的值
        const username = ref('');
        const password = ref('');
        const email = ref('');
        // 显示的文字
        const button_text = ref('登陆')
        const text_info_prefix = ref('还没有账号？');
        const text_info = ref('注册');
        // 当前页面状态
        const islogin = ref(true);

        const toreg_or_login = () => {
            if (islogin.value) {
                // 相当于点了蓝色注册小字
                islogin.value = false;
                text_info_prefix.value = '已有账号？';
                text_info.value = '登陆';
                button_text.value = '注册';
                username.value = "";
                password.value = "";
                email.value = "";
                return;
            }
            // 相当于点击了蓝色登陆小字
            islogin.value = true;
            text_info_prefix.value = '还没有账号？';
            text_info.value = '注册';
            button_text.value = '登陆';
        }
        const reg_or_login = async () => {
            if (islogin.value) {
                if (username.value == '' || password.value == '') {
                    console.log("输入不能为空。");
                    return;
                }
                // 下面写登录(未加密)

                try {
                    const res = await login_request(username.value, password.value);
                    // 后端回复的消息
                    if (res['data']['status'] == 'success') {
                        console.log("登录成功。");
                        // 设置登陆状态
                        login_status.value.login(res['data']['username'], res['data']['email'], res['data']['token'])
                        // 导航到主页
                        window.location.href = "/";
                    } else {
                        alert(res['data']['message']);
                        console.log(res['data']['message']);
                    }
                    return;
                } catch (e) {
                    alert("登录失败。");
                    console.log(e);
                    return;
                }


            }
            if (email.value == '' || username.value == '' || password.value == '') {
                console.log("输入不能为空。");
                return;
            }

            // 下面写注册(未加密)
            try {
                const res = await register_request(username.value, password.value, email.value);
                if (res['data']['status'] == 'success') {
                    username.value = "";
                    password.value = "";
                    email.value = "";
                    islogin.value = true;
                    text_info_prefix.value = '还没有账号？';
                    text_info.value = '注册';
                    button_text.value = '登陆';
                    alert(res['data']['message']);
                }
                else
                    console.log(res['data']['message']);
            }
            catch (e) {
                alert("注册失败。");
                console.log(e);
                return;
            }
        }

        return {
            reg_or_login, toreg_or_login, text_info_prefix, text_info, button_text, islogin, password, email, username
        }
    }
};
</script>

<style scoped src="@/assets/css/loginpage.css"></style>