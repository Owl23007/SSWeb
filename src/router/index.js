import { createRouter, createWebHistory } from 'vue-router'
import loginPage from '../components/LoginComponent.vue'
import homePage from '../components/Home.vue'
import textPage from '../components/Text.vue'
import UserInfo from '../components/UserInfo.vue'

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
    path: '/text',
    name: 'textPage',
    component: textPage,
    meta:{
      title: '文章'
    }
  },
  {
    path: '/userinfo',
    name: 'userinfoPage',
    component: UserInfo,
    meta:{
      title: '用户信息'
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

export default router