import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import LoginView from './views/Login.vue'

import './plugin'


Vue.config.productionTip = false

Vue.use(VueRouter)

const router = new VueRouter({
  routes:[
    {path: '/login' ,component: LoginView },
  ]
})

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
