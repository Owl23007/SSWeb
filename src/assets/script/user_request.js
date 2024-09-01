import axios from 'axios';
import sha256 from 'crypto-js/sha256';

export const register_post = async (username, email, password) => {
    if (username === '' || password === '' || email === '') {
        alert("请输入用户名、邮箱和密码。");
        return;
    }
    const encryptedPassword = sha256(password).toString();
    const response = await axios.post('http://localhost:8080/user/register',
        { username: username, email: email, password: encryptedPassword }, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
    return response.data;
};

export const login_post = async (username, password) => {
    if (username === '' || password === '') {
        alert("请输入用户名和密码。");
        return;
    }
    const encryptedPassword = sha256(password).toString();
    const response = await axios.post('http://localhost:8080/user/login',
        { username: username, password: encryptedPassword }, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });
    return response.data;
};

export const deleteAcc_delete = async (token, password) => {
    if (password === '') {
        alert("请输入密码。");
        return;
    }
    const encryptedPassword = sha256(password).toString();
    console.log(encryptedPassword);
    const response = await axios.post('http://localhost:8080/user/unregister',
        { password: encryptedPassword },
        {
            headers: {
                Authorization: `Duel ${token}`,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }
    );
    return response.data;
};

export const updateAvatar_patch = async (token, avatar) => {
    const response = await axios.patch('http://localhost:8080/user/updateAvatar',
        { url: avatar },
        {
            headers: {
                Authorization: `Duel ${token}`,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }
    );
    return response.data;
}

export const updateBackground_patch = async (token, background) => {
    const response = await axios.patch('http://localhost:8080/user/updateBackground',
        { url: background },
        {
            headers: {
                Authorization: `Duel ${token}`,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }
    );
    return response.data;
}

export const updateUserInfo_put = async (token, nickname, signature) => {
    const response = await axios.put('http://localhost:8080/user/update',
        { nickname: nickname, signature: signature },
        {
            headers: {
                Authorization: `Duel ${token}`,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }
    );
    return response.data;
}