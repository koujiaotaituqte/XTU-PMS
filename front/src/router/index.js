import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', redirect: '/Manager'},
    {path: '/manager',name: 'home',component: () => import('../views/Manager.vue'),
      children: [
        {path: 'home',meta: {name: '主页'},component: () => import('../views/Home.vue')},
        {path: 'admin',meta: {name: '管理员信息'},component: () => import('../views/Admin.vue')},
        {path: 'user',meta: {name: '用户信息'},component: () => import('../views/User.vue')},

        {path: 'hotel',component: () => import('../views/Hotel.vue')},
        {path: 'hotelroom',component: () => import('../views/HotelRoom.vue')},
        {path: 'roomcustomer',component: () => import('../views/RoomCustomer.vue')},
        {path: 'bookcustomer',component: () => import('../views/BookCustomer.vue')}
      ]
    },
    {path: '/login',component: ()=>import('../views/Login.vue'),},
    {path: '/register',component: ()=>import('../views/Register.vue'),},

    {path: '/notFound',component: () => import('../views/404.vue'),},
    {path: '/:pathMatch(.*)', redirect: '/notFound'},
  ],
})

export default router
