import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeViews.vue'
import GenderManagement from "@/views/GenderManagement.vue";
import CompanyManagement from "@/views/CompanyManagement.vue";
import UserManagement from "@/views/UserManagement.vue";

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
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
