<template>
  <div class="card" style="width: 50%; align-items: center;margin-left:50px ">
    <h3 style="text-align: center">个人中心</h3>
    <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width = "80px" style="padding: 20px 30px 10px 0"> <!--上右下左-->
      <el-form-item prop="username" label="账号">
        <el-input v-model="data.user.username" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="name" label="名称">
        <el-input v-model="data.user.name" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="phone" label="电话">
        <el-input v-model="data.user.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input v-model="data.user.email" autocomplete="off" />
      </el-form-item>
    </el-form>
    <div style="text-align: center">
      <el-button type="primary" @click="update">保存</el-button>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";

const data = reactive({
  user: JSON.parse(localStorage.getItem('code_user')),

})

const update =()=>{
  let url
  if(data.user.role === 'ADMIN'){
    url='/admin/update'
  }
  if(data.user.role === 'USER'){
    url='/user/update'
  }
  request.put(url,data.user).then(res=>{
    if(res.code==='200'){
      ElMessage.success("更新成功")
      localStorage.setItem("code_user",JSON.stringify(data.user))
    }else{
      ElMessage.error(res.msg)
    }
  })
}
</script>