import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomePage from '@/views/HomePage.vue';
import ProductPage from '@/views/ProductPage.vue';
import LayoutsComponents from '@/components/layouts/LayoutsComponents.vue';

const routes: Array<RouteRecordRaw> = [

  {
    path: "/",
    component: LayoutsComponents,
    children: [
      { path: "", name: "Home", component: HomePage },
    ],
  },
  {
    path: "/products",
    component: LayoutsComponents,
    children: [
      { path: "", name: "Product", component: ProductPage },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
