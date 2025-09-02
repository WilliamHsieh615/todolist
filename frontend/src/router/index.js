import {createRouter, createWebHashHistory} from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import RetrieveView from '@/views/RetrieveView.vue'
import TodoListPageView from '@/views/TodoListPage/TodoListPageView.vue'
import TodoListAllView from '@/views/TodoListPage/TodoListAllView.vue'
import TodoListActiveView from '@/views/TodoListPage/TodoListActiveView.vue'
import TodoListCompletedView from '@/views/TodoListPage/TodoListCompletedView.vue'
import NotFoundView from '@/views/NotFoundView.vue'

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
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
            path: '/todolist-page',
            name: 'todolist-page',
            component: TodoListPageView,
            children: [
                {
                    path: '',
                    name: 'todolist.all',
                    component: TodoListAllView
                },
                {
                    path: 'active',
                    name: 'todolist.active',
                    component: TodoListActiveView
                },
                {
                    path: 'completed',
                    name: 'todolist.completed',
                    component: TodoListCompletedView
                }
            ]
        },
        {
            path: '/:pathMatch(.*)*',
            name: 'NotFound',
            component: NotFoundView
        },
    ],
})

export default router
