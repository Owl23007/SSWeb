import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000,
    headers: {'X-Custom-Header': 'foobar'}
});

export const register_request = async (data) => {
    try {
        const response = await instance.post('/api/register', data);
        return response.data;
    } catch (error) {
        console.error('Error during registration:', error);
        throw error;
    }
};

export const login_request = async (data) => {
    try {
        const response = await instance.post('/api/login', data);
        return response.data;
    } catch (error) {
        console.error('Error during login:', error);
        throw error;
    }
};

// 添加缺失的函数
export const request_text_list = async () => {
    try {
        const response = await instance.get('/api/texts');
        return response.data;
    } catch (error) {
        console.error('Error fetching text list:', error);
        throw error;
    }
};

export const request_add_text = async (data) => {
    try {
        const response = await instance.post('/api/texts', data);
        return response.data;
    } catch (error) {
        console.error('Error adding text:', error);
        throw error;
    }
};