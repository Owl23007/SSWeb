import axios from 'axios';


export const register_post = async (username, email, password) => {
    if (username === '' || password === '' || email === '') {
        alert("请输入用户名、邮箱和密码。");
        return;
    }
    const response = await axios.post('http://localhost:8080/user/register',
        { username: username, email: email, password: password }, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
    return response.data;
};

export const login_post = async (username, password) => {
    if (username === '' || password === '') {
        alert("请输入用户名和密码。");
        return;
    }
    const response = await axios.post('http://localhost:8080/user/login',
        { username: username, password: password }, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });
    return response.data;
};