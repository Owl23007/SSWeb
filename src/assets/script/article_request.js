import axios from 'axios';

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

export const getCategory_get = async (token, categoryId) => {
    const response = await axios.get('http://localhost:8080/category/getCategoryById', {
        params: {
            categoryId: categoryId
        },
        headers: {
            'Authorization': 'Duel ' + token
        }
    });
    return response.data;
};

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

export const getArticleByID_get = async (token, articleId) => {
    const response = await axios.get('http://localhost:8080/article/article', {
        params: {
            articleId: articleId
        },
        headers: {
            'Authorization': 'Duel ' + token
        }
    });
    return response.data;
};

export const deleteArticle_delete = async (token, articleId) => {
    const response = await axios.delete('http://localhost:8080/article/delete', {
        params: {
            articleId: articleId
        },
        headers: {
            'Authorization': 'Duel ' + token
        }
    });
    return response.data;
};

export const getArticles_get = async (token, pageNum, pageSize) => {
    const response = await axios.get('http://localhost:8080/article/list',
        {
            params: {
                pageNum: pageNum,
                pageSize: pageSize
            },
            headers: {
                'Authorization': 'Duel ' + token
            }
        }
    );
    return response.data;
};

export const getUserArticles_get = async (token) => {
    const response = await axios.get('http://localhost:8080/article/userArticle', {
        headers: {
            'Authorization': 'Duel ' + token
        }
    });
    return response.data;
}