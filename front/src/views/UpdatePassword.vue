<template>
  <div class="card" style="width: 50%; align-items: center;margin-left:50px ">
    <h3 style="text-align: center">修改密码</h3>
    <el-form :model="form" ref="formRef">
      <el-form-item prop="oldPassword" label="旧的密码" :rules="[
        { required: true, message: '请输入旧密码', trigger: 'blur' },
        { validator: validateOldPassword, trigger: 'blur' }
      ]">
        <el-input show-password type="password" size="large" v-model="form.oldPassword" autocomplete="off" prefix-icon="Lock" placeholder="请输入旧密码"/>
      </el-form-item>
      <el-form-item prop="newPassword" label="新的密码" :rules="[
        { required: true, message: '请输入新密码', trigger: 'blur' },
        { validator: validateNewPassword, trigger: 'blur' }
      ]">
        <el-input show-password type="password" size="large" v-model="form.newPassword" autocomplete="off" prefix-icon="Lock" placeholder="请输入新密码"/>
      </el-form-item>
      <el-form-item prop="confirmPassword" label="确认密码" :rules="[
        { required: true, message: '请确认新密码', trigger: 'blur' },
        { validator: validateConfirmPassword, trigger: 'blur' }
      ]">
        <el-input show-password type="password" size="large" v-model="form.confirmPassword" autocomplete="off" prefix-icon="Lock" placeholder="请确认新密码"/>
      </el-form-item>
    </el-form>
    <div style="text-align: center">
      <el-button type="primary" @click="submitForm">保存</el-button>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import { ElMessage } from 'element-plus'
import request from "@/utils/request.js";

const formRef = ref()
const data = reactive({
  user: JSON.parse(localStorage.getItem('code_user')) || { password: '' }
})

const form = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})

// 验证旧密码是否正确
const validateOldPassword = (rule, value, callback) => {
  if (value !== data.user.password) {
    callback(new Error('旧密码不正确'))
  } else {
    callback()
  }
}

// 验证新旧密码是否相同
const validateNewPassword = (rule, value, callback) => {
  if (value === form.oldPassword) {
    callback(new Error('新密码不能与旧密码相同'))
  } else {
    callback()
  }
}

// 验证确认密码是否匹配
const validateConfirmPassword = (rule, value, callback) => {
  if (value !== form.newPassword) {
    callback(new Error('两次输入的密码不一致'))
  } else {
    callback()
  }
}

const submitForm = () => {
  formRef.value.validate(valid => {
    if (valid) {
      updatePassword()
    } else {
      ElMessage.error('请检查输入是否正确')
      return false
    }
  })
}

const updatePassword = () => {
  data.user.password=form.newPassword
  request.put('/updatepassword',data.user).then(res=>{
    if(res.code==='200'){
      ElMessage.success('修改成功')
    }else{
      ElMessage.error(res.msg)
    }
  })
}
</script>