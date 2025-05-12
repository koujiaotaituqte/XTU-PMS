import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', redirect: '/Manager'},
    {path: '/manager',name: 'home',component: () => import('../views/manager/Manager.vue'),
      children: [
        {path: 'home',meta: {name: '主页'},component: () => import('../views/Home.vue')},
        {path: 'admin',meta: {name: '管理员信息'},component: () => import('../views/manager/Admin.vue')},
        {path: 'user',meta: {name: '用户信息'},component: () => import('../views/manager/User.vue')},
        {path: 'notice',meta: {name: '系统公告'},component:()=>import('../views/manager/Notice.vue')},
        {path: 'person',meta: {name: '个人中心'},component:()=>import('../views/Person.vue')},
        {path: 'updatepassword',meta: {name: '个人中心'},component:()=>import('../views/UpdatePassword.vue')},

        {path: 'roomCustomer',component: () => import('../views/manager/RoomCustomer.vue')},
        {path: 'bookCustomer',component: () => import('../views/manager/BookCustomer.vue')},
        {path: 'roomShow',component: () => import('../views/manager/roomShow.vue')}
      ]
    },
    {path: '/customer',name: 'user',component: ()=> import('../views/customer/Customer.vue'),
      children: [
        {path: 'updatepassword',meta: {name: '个人中心'},component:()=>import('../views/UpdatePassword.vue')},
        {path: 'person',meta: {name: '个人中心'},component:()=>import('../views/Person.vue')},
        {path: 'hotelNotice',component: () => import('../views/customer/HotelNotice.vue')},
        {path: 'home',meta: {name: '主页'},component: () => import('../views/Home.vue')},
        {path: 'hotelRoom',component: () => import('../views/customer/HotelRoom.vue')},
      ]
    },
    {path: '/login',component: ()=>import('../views/login/Login.vue'),},
    {path: '/register',component: ()=>import('../views/login/Register.vue'),},

    {path: '/notFound',component: () => import('../views/404/404.vue'),},
    {path: '/:pathMatch(.*)', redirect: '/notFound'},
  ],
})

export default router
