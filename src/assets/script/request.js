import axios from 'axios'

async function request_add_text(text_content, text_title, name) {
    const res = await axios({
        url: 'http://localhost:8080/api/addtext',//请求地址
        method: 'post',//请求方式
        data: {//请求参数
            Text_content: text_content,
            Text_title: text_title,
            username: name
        },
    })
    return res;
}
async function request_text_list() {
    const res = await axios({
        url: 'http://localhost:8080/api/gettext',
        method: 'post',
        data: {//请求参数
            Text_time: 123456
        },
    })
    return res;
}

async function register_request(username, password, email) {
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

async function login_request(username, password) {
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

export { request_text_list, request_add_text, register_request, login_request }