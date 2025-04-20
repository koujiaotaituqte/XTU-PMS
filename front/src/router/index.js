import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', redirect: '/Manager'},
    {path: '/manager',name: 'home',component: () => import('../views/Manager.vue'),
      children: [
        {path: 'home',component: () => import('../views/Home.vue')},
        {path: 'admin',component: () => import('../views/Admin.vue')},
        {path: 'hotel',component: () => import('../views/Hotel.vue')}
      ]
    },
    {path: '/notFound',component: () => import('../views/404.vue'),},
    {path: '/:pathMatch(.*)', redirect: '/notFound'},
  ],
})

export default router
