export class Article {
    id;
    title;
    content;
    coverImg;
    state;
    categoryId;
    createUser;
    createTime;

    constructor(id, title, content, coverImg, state, categoryId, createUser, createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.coverImg = coverImg;
        this.state = state;
        this.categoryId = categoryId;
        this.createUser = createUser;
        this.createTime = createTime;
    }
}

export class PreviewArticle {
    id;
    title;
    coverImg;
    category;
    categoryId;
    createUser;
    createTime;

    constructor(id, title, coverImg, categoryId, createUser, createTime) {
        this.id = id;
        this.title = title;
        this.coverImg = coverImg;
        this.categoryId = categoryId;
        this.createUser = createUser;
        this.createTime = createTime;
    }
}