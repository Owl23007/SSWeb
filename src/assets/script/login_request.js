import axios from 'axios';


export const register_post = async (username, email, password) => {
    const response = await axios.post('http://localhost:8080/user/register',
        { username: username, email: email, password: password }, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    })
    return response;
};

export const login_post = async (username, password) => {
    const response = await axios.post('http://localhost:8080/user/login',
        { username: username, password: password }, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });
    return response;
};