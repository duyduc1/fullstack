import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeViews.vue'
import GenderManagement from "@/views/GenderManagement.vue";
import CompanyManagement from "@/views/CompanyManagement.vue";
import UserManagement from "@/views/UserManagement.vue";
import Register from "@/components/fearture/RegisterForm.vue";
import LoginForm from '@/components/fearture/LoginForm.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/gender',
    name : 'genders',
    component: GenderManagement
  },
  {
    path:'/company',
    name: 'company',
    component: CompanyManagement
  },
  {
    path:'/user',
    name: 'User',
    component: UserManagement
  },
  {
    path:'/register',
    name:'RegisterForm',
    component:Register
  },
  {
    path:'/login',
    name:'LoginForm',
    component:LoginForm
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
