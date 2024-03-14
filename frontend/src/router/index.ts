import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ChangeView from '@/views/ChangeView.vue'
import DetailedView from '@/views/DetailedView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/create',
      name: 'create',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/CreateView.vue')
    },
    { path: '/change/:id', component: ChangeView },
    { path: '/book/:id', component: DetailedView },
  ]
})

export default router
