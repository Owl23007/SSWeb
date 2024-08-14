import axios from 'axios'
async function request_text_list() {
    return await axios.get('http://127.0.0.1:8080/api/text/list/')
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

export { request_text_list, register_request, login_request }