<template>
  <div class="bg">
    <div style="width: 400px;background-color: #e0e3e8;opacity: 0.8;border-radius:5px;box-shadow: 0 0 10px rgba(0,0,0,0.1);padding: 40px 20px">
      <el-form status-icon ref="formRef" :model="data.form" :rules="data.rules"> <!--上右下左-->
        <div style="margin: 10px 0 30px 0;text-align: center;font-weight:bold;font-size: 20px ">欢 迎 注 册</div>
        <el-form-item prop="username" label="">
          <el-input size="large" v-model="data.form.username" autocomplete="off" prefix-icon="User" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item prop="password" label="">
          <el-input size="large" show-password v-model="data.form.password" autocomplete="off" prefix-icon="Lock" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item prop="confirmPassword" label="">
          <el-input size="large" show-password v-model="data.form.confirmPassword" autocomplete="off" prefix-icon="Lock" placeholder="请再次确认密码"/>
        </el-form-item>
        <div>
          <el-button size="large" style="width: 100%" type="primary" @click="register">注 册</el-button>
        </div>
        <div style="text-align: right;margin-top: 5px">
          已有账号，请<a style="font-weight: bold;color: #2c82ff" href="/login">登录</a>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>

import { reactive,ref } from "vue";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import request from "@/utils/request.js";

const validatePass = (rule, value,callback) =>{
  if(value !== data.form.password){
    callback(new Error('两次密码不一致'))
  }else {
    callback()
  }
}

const formRef =ref()
const data = reactive({
  form: {},
  rules: {
    username:[
      {required: true, message: '请填写账号', trigger: 'blur'}
    ],
    password:[
      {required: true, message: '请填写密码', trigger: 'blur'}
    ],
    confirmPassword:[
      {required: true, message: '请确认密码',validator:validatePass, trigger: 'blur'}
    ],
  }
})



const register =()=>{
  formRef.value.validate((valid)=>{
    if(valid){
      request.post('/register',data.form).then(res => {
        if(res.code === '200'){
          localStorage.setItem("code_user",JSON.stringify(res.data || {}))//浏览器中持久存储数据的机制
          ElMessage.success('注册成功')
          router.push('/login')
        }else{
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

</script>

<style scoped>
.bg {
  display: flex;
  justify-content: flex-end;  /* 内容靠右 */
  align-items: center;       /* 内容垂直居中 */
  height: 100vh;            /* 确保全屏高度 */
  overflow: hidden;
  background-image: url("@/assets/imgs/bg1.jpg");
  background-size: cover;
  background-position: center center; /* 关键：图片水平和垂直居中 */
  background-repeat: no-repeat;
  padding-right: 30vh;      /* 右侧留白 */
}
</style>