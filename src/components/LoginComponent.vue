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
                        <input type="email" v-model="email" name="email" autocomplete="email" required />
                    </div>
                </div>
                <div class="form-group">
                    <label for="loginPassword">密码:</label>
                    <div class="input_box">
                        <input type="password" v-model="password" name="password" autocomplete="current-password" required />
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
            <div class="test-link">
                <button @click="test_request">Test 请求</button>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
    name: 'LoginComponent',
    setup() {
        const username = ref('');
        const password = ref('');
        const email = ref('');
        const islogin = ref(true); 
        const text_info_prefix = ref('还没有账号？');
        const text_info = ref('注册');
        const button_text = ref('登陆');

        const login_request = async (credentials) => {
            try {
                const res = await axios.post('http://localhost:8080/user/login', credentials, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                return res.data;
            } catch (e) {
                console.log(e);
                return { status: 'error', message: '登录请求失败' };
            }
        };

        const register_request = async (credentials) => {
      try {
        
        const res = await axios.post('http://localhost:8080/user/register', credentials, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        console.log(res.data);
        return res.data;
      } catch (e) {
        console.log(e);
        return { status: 'error', message: '注册请求失败' };
      }
    };

    const handleSubmit = async () => {
      const credentials = {
        username: username.value,
        password: password.value,
        email: email.value
      };

      if (!credentials.username || !credentials.password || (!islogin.value && !credentials.email)) {
        alert('请填写所有必填字段');
        return;
      }

      let response;
      if (islogin.value) {
        response = await login_request(credentials);
      } else {
        response = await register_request(credentials);
      }

      if (response.status === 'success') {
        console.log(islogin.value ? '登录成功' : '注册成功');
        if (!islogin.value) {
          islogin.value = true;
          text_info_prefix.value = '还没有账号？';
          text_info.value = '注册';
          button_text.value = '登陆';
        }
      } else {
        alert(response.message);
        console.log(response.message);
      }
    };


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
                    console.log(e);
                }
            } else {
                if (username.value === '' || password.value === '' || email.value === '') {
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
                        console.log("注册成功。");
                        islogin.value = true;
                        text_info_prefix.value = '还没有账号？';
                        text_info.value = '注册';
                        button_text.value = '登陆';
                    } else {
                        alert(res.message);
                        console.log(res.message);
                    }
                } catch (e) {
                    console.log(e);
                }
            }
        };

        const test_request = () => {
            axios.get('http://localhost:8080/user/test')
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.log("Test 请求失败：", error);
                });
        };

        const toreg_or_login = () => {
            islogin.value = !islogin.value;
            if (islogin.value) {
                text_info_prefix.value = '还没有账号？';
                text_info.value = '注册';
                button_text.value = '登陆';
            } else {
                text_info_prefix.value = '已有账号？';
                text_info.value = '登陆';
                button_text.value = '注册';
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
               test_request,
               handleSubmit
        };
    }
};
</script>

<style scoped src="@/assets/css/loginpage.css"></style>