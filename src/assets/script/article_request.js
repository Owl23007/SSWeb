import axios from 'axios';

export const addArticle_post = async (token, title, content, coverImg, state, categoryId) => {
    if (title === '' || content === '' || coverImg === '') {
        alert("请填写完整信息");
        return false;
    }
    const response = await axios.post('http://localhost:8080/article/add',
        { title: title, content: content, coverImg: coverImg, state: state, categoryId: categoryId }, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Duel ' + token
        }
    })
    return response.data;
};

export const addCategory_post = async (token, categoryName, categoryAlias) => {
    if (categoryName === '' || categoryAlias === '') {
        alert("请填写完整信息");
        return;
    }
    const response = await axios.post('http://localhost:8080/category/add',
        { categoryName: categoryName, categoryAlias: categoryAlias }, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Duel ' + token
        }
    })
    return response.data;
};