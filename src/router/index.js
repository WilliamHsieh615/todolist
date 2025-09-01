import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import RetrieveView from '@/views/RetrieveView.vue'
import InternalPageView from '@/views/InternalPageView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView,
    },
    {
      path: '/retrieve',
      name: 'retrieve',
      component: RetrieveView,
    },
    {
      path: '/internal-page',
      name: 'internal-page',
      component: InternalPageView,
    },
  ],
})

export default router
