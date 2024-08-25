import { createRouter, createWebHistory } from 'vue-router'
import loginPage from '../components/LoginComponent.vue'
import homePage from '../components/Home.vue'
import UserInfo from '../components/UserInfo.vue'
import ArticleList from '../components/ArticleList.vue'
import store from '@/store'; // 假设你的 Vuex store 文件路径是 '@/store'

const routes = [
  {
    path: '/',
    redirect: '/home' // 设置重定向到 /home
  },
  {
    path: '/login',
    name: 'loginPage',
    component: loginPage,
    meta:{
      title: '登录'
    }
  },
  {
    path: '/article',
    name: 'ArticleList',
    component: ArticleList,
    meta:{
      title: '文章'
    }
  },
  {
    path: '/userinfo',
    name: 'userinfoPage',
    component: UserInfo,
    meta:{
      title: '用户信息',
      requiresAuth: true // 需要认证
    }
  },
  {
    path: '/home',
    name: 'homePage',
    component: homePage,
    meta:{
      title: '首页'
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 设置页面标题和路径守卫
router.beforeEach((to, from, next) => {
  document.title = to.meta.title || '默认标题';
  
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 需要认证的路由
    if (!store.state.isLoggedIn) {
      // 如果用户未登录，重定向到登录页面
      next({ path: '/login' });
    } else {
      // 用户已登录，继续导航
      next();
    }
  } else {
    // 不需要认证的路由，继续导航
    next();
  }
});

export default router