import { createRouter, createWebHashHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'login',
            component: () => import('@/views/LoginView.vue')
        },
        {
            path: '/register',
            name: 'register',
            component: () => import('@/views/RegisterView.vue')
        },
        {
            path: '/retrieve',
            name: 'retrieve',
            component: () => import('@/views/RetrieveView.vue')
        },
        {
            path: '/todolist-page',
            name: 'todolist-page',
            redirect: '/todolist-page/all',
            component: () => import('@/views/TodoListPage/TodoListPageView.vue'),
            meta: { requiresAuth: true },
            children: [
                {
                    path: 'all',
                    name: 'todolist.all',
                    component: () => import('@/views/TodoListPage/TodoListAllView.vue')
                },
                {
                    path: 'active',
                    name: 'todolist.active',
                    component: () => import('@/views/TodoListPage/TodoListActiveView.vue')
                },
                {
                    path: 'completed',
                    name: 'todolist.completed',
                    component: () => import('@/views/TodoListPage/TodoListCompletedView.vue')
                }
            ]
        },
        {
            path: '/:pathMatch(.*)*',
            name: 'NotFound',
            component: () => import('@/views/NotFoundView.vue')
        },
    ],
})

// 全域守衛
router.beforeEach((to, from, next) => {
    const authStore = useAuthStore()
    if (to.matched.some(record => record.meta.requiresAuth) && !authStore.isLoggedIn) {
        return next({ path: '/', query: { message: 'not-logged-in' } })
    }
    next()
})

export default router
