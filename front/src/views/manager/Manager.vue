<template>
    <div>
        <!--头部区域-->
        <div style="height: 60px; border-bottom: 1px solid #7F8A96; display: flex; align-items: center;">
             <!--左边标题-->
            <div style="display: flex; align-items: center;">
                <img style="width: 100px;border-radius: 50%; margin-left: 10px;margin-right: 5px;" src="../../assets/imgs/logo.png" alt="">
                
                <span style="font-size: 18px; font-weight: bold; color:#65A87D">
                    湘大酒店管理系统
                </span>

            </div>

            <div style="flex: 1"></div>
            <!--右边头像，名称-->
            <div style="width: fit-content; padding-right: 20px;display: flex; align-items: center;">
                
                <el-dropdown>
                    <div style="display: flex; align-items: center;">
                      <img v-if="data.user?.avatar" style="width: 40px; height: 40px; border-radius: 50%;margin-right: 5px;" :src="data.user?.avatar" alt="">
                      <img v-else style="width: 40px; height: 40px; border-radius: 50%;margin-right: 5px;" src="../../assets/imgs/manager.jpg" alt="">
                        <span>
                            {{ data.user?.name }}
                        </span>
                    </div>

                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="router.push('/manager/person')">个人信息</el-dropdown-item>
                            <el-dropdown-item @click="router.push('/manager/updatepassword')">修改密码</el-dropdown-item>
                            <el-dropdown-item @click="login_out">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>

            </div>

        </div>

        <!--下方区域-->
        <div style="display: flex;">
            
            <!--菜单-->
            <div style="width: 240px;">
                <el-menu  router :default-active="router.currentRoute.value.path" style=" min-height: calc(100vh - 60px);">
                    <el-menu-item index="/manager/home">
                        <el-icon><House /></el-icon>
                        <span>首页</span>
                    </el-menu-item>

                    <el-sub-menu index="1">
                        <template #title>
                        <el-icon><user /></el-icon>
                        <span>用户管理</span>
                        </template>
                        <el-menu-item index="/manager/admin">管理员信息</el-menu-item>
                        <el-menu-item index="/manager/user">用户信息</el-menu-item>
                    </el-sub-menu>

                    <el-sub-menu index="2">
                      <template #title>
                        <el-icon><Monitor /></el-icon>
                       <span>信息管理</span>
                      </template>
                      <el-menu-item index="/manager/notice">系统公告</el-menu-item>
                    </el-sub-menu>

                    <el-sub-menu index="3">
                      <template #title>
                      <el-icon><Histogram /></el-icon>
                      <span>酒店</span>
                      </template>
                      <el-menu-item index="/manager/roomCustomer">入住信息</el-menu-item>
                      <el-menu-item index="/manager/bookCustomer">预定信息</el-menu-item>
                      <el-menu-item index="/manager/roomShow">房源展示</el-menu-item>
                    </el-sub-menu>

                </el-menu>
                
            </div>

            <!--数据渲染-->
            <div style="flex: 1; width: 0; padding: 10px; background-color: #f6fafb;">
                <RouterView></RouterView>
            </div>

        </div>


    </div>
</template>

<script setup>
import router from '@/router/index.js';
import {Histogram, House, Monitor} from "@element-plus/icons-vue";
import {reactive} from "vue";
import HotelNotice from "@/views/customer/HotelNotice.vue";

const data = reactive({
  user: JSON.parse(localStorage.getItem('code_user'))
})

const login_out = ()=> {
  localStorage.removeItem('code_user')
  //router.push('/login')
  window.location.href = '/login'
}

if(!data.user?.id){
  window.location.href = '/login'
}

if(!(data.user.role === 'ADMIN')){
  window.location.href = '/customer'
}

</script>

<style>
.el-menu {
    background-color: aliceblue;
}
.el-sub-menu__title {
    color: black;
    font-size: 14px;
    background-color: aliceblue;
}
.el-menu-item {
    color: black;
    font-size: 14px;
}
.el-menu .is-active {
    background-color:#88BFFF ;
    color:black;
}
.el-tooltip__trigger {
    outline: none;
}
.er-dropdown {
    cursor: pointer;
}
.el-menu--inline .el-menu-item {
    padding-left: 60px !important;
}
</style>