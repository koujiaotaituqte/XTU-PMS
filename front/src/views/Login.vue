<template>
    <div class="loginview">
      <div class="login">
        <el-form 
        size="mini"
        :model="ruleForm"
         status-icon :rules="rules" 
         ref="ruleForm" label-width="60px" 
         class="demo-ruleForm">

    <el-form-item label="账号" prop="loginid">
      <el-input type="text" 
      v-model="ruleForm.loginid"
       autocomplete="off">
      </el-input>
    </el-form-item>

    <el-form-item label="密码" prop="loginpwd">
      <el-input type="password"
       v-model="ruleForm.loginpwd"
        autocomplete="off">
      </el-input>
    </el-form-item>
 
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
      <el-button @click="resetForm('ruleForm')">取消</el-button>
    </el-form-item>
  </el-form>
      </div>

    </div>
</template>

  <script>
    export default {
        name:'LoginView',
      data() {
        
        var validateloginid = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入账号'));
          }else {
            callback();
          }
        };

        var validateloginpwd = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          }else {
            callback();
          }
        };

        return {
          ruleForm: {
            //名称
            loginid: '',
            //密码
            loginpwd: '',

          },
          //验证
          rules: {
            loginid:[
            { validator: validateloginid, trigger: 'blur' }
            ],
            //验证密码
            loginpwd: [
              { validator: validateloginpwd, trigger: 'blur' }
            ],
          }
        };
      },
      methods: {
        //表单提交
        submitForm(formName) {
          this.$refs[formName].validate(async(valid) => {
            if (valid) {
              //实现登录
              let res = await this.$get('/Admin/Login',this.ruleForm);
              console.log(res);
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        //表单重置
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
  </script>

  <style scoped lang="scss">
  .loginview{
    display: flex;
    justify-content: center;
    align-items: center;
    height:  100vh;
    .login{
      width: 460px;
      height: 220px;
      border:  1px solid #ccc;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
</style>