import { createRouter, createWebHistory } from 'vue-router'
import loginPage from '../components/LoginComponent.vue'
import homePage from '../components/Home.vue'

const routes = [
  {
    path: '/login',
    name: 'loginPage',
    component: loginPage
  },
  {
    path: '/',
    name: 'HomePage',
    component: homePage
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router