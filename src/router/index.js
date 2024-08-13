import { createRouter, createWebHistory } from 'vue-router'
import loginPage from '../components/LoginComponent.vue'
import homePage from '../components/Home.vue'

const routes = [
  {
    path: '/login',
    name: 'loginPage',
    component: loginPage,
    meta:{
      title: '登录'
    }
  },
  {
    path: '/',
    name: 'HomePage',
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