<template>
  <div class="card" style="margin-bottom: 5px;">
    <el-input clearable @clear='load' style="margin-right: 5px;width: 260px;" v-model="data.username" placeholder="请输入账号查询" ></el-input>
    <el-input clearable @clear='load' style="margin-right: 5px;width: 260px;" v-model="data.name" placeholder="请输入名称查询" ></el-input>
    <el-button type="primary" @click="load">查 询</el-button>
    <el-button type="info" @click="reset">重 置</el-button>
  </div>

  <div class="card" style="margin-bottom: 5px;">
    <el-button type="info" @click="handleAdd">新增</el-button>
    <el-button type="danger" @click="deleteBatch">批量删除</el-button>
    <el-upload
        style="display: inline-block; margin-left: 10px"
        action="http://localhost:9090/user/import"
        :show-file-list="false"
        :on-success="handleImportSuccess"
    >
      <el-button type="success">批量导入</el-button>
    </el-upload>
    <el-button type="warning" @click="exportData" style=" margin-left: 10px">批量导出</el-button>

  </div>

  <div class="card" style="margin-bottom: 5px;">
    <el-table :data="data.tableData" style="width: 100%" @selection-change="handleSelectionChange" :header-cell-style="{color: 'black'}">
    <el-table-column type="selection" width="55"/>
      <el-table-column label="头像">
        <template #default="scope">
          <el-image v-if="scope.row.avatar" :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]" :preview-teleported="true"
                    style="width: 40px; height: 40px; border-radius: 50%; display: block" />
        </template>
      </el-table-column>
    <el-table-column prop="username" label="账号"  />
    <el-table-column prop="name" label="名称" />
    <el-table-column prop="phone" label="电话" />
    <el-table-column prop="email" label="邮箱" />
    <el-table-column label="操作" width="100">
      <template #default="scope">
        <el-button type="primary" icon="Edit" circle @click="handleEdit(scope.row)"></el-button>
        <el-button type="danger" icon="Delete" circle @click="del(scope.row.id)"></el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>

  <div class="card">
    <el-pagination
      v-model:current-page="data.pageNum"
      v-model:page-size="data.pageSize"
      layout="total, sizes,prev, pager, next,jumper"
      :page-sizes="[5,10,20]"
      :total="data.total"
      @size-change="load"
      @current-change="load"
    />
  </div>

  <el-dialog v-model="data.formVisible" title="用户信息" width="50%" destroy-on-close>
    <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width = "80px" style="padding: 20px 30px 10px 0"> <!--上右下左-->
      <el-form-item prop="username" label="账号">
        <el-input v-model="data.form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="name" label="名称">
        <el-input v-model="data.form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="phone" label="电话">
        <el-input v-model="data.form.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="email" label="邮箱">
        <el-input v-model="data.form.email" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="avatar" label="头像">
        <el-upload action="http://localhost:9090/files/upload"
        :on-success="handleFileSuccess"
        :headers="{ token: data.user.token }"
        :list-type="'picture'">
          <el-button type="primary">
            上传头像
          </el-button>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </div>
    </template>
  </el-dialog>
</template>


<script setup>
import { reactive, ref} from 'vue';
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {formToJSON} from "axios";



const data = reactive({
  user: JSON.parse(localStorage.getItem('code_user') || '{}'),
  name: null,
  username: null,
  pageNum: 1,
  pageSize: 5,
  total: 0,
  tableData: [],
  formVisible:false,
  form:{},
  rules:{
    username:[
      {required: true, message: '请填写账号', trigger: 'blur'}  //必填,trigger是什么时候触发
    ],
    name:[
      {required: true, message: '请填写名称', trigger: 'blur'}  //必填,trigger是什么时候触发
    ],
    phone:[
      {required: true, message: '请填写手机号', trigger: 'blur'}  //必填,trigger是什么时候触发
    ],
    email:[
      {required: true, message: '请填写邮箱', trigger: 'blur'}  //必填,trigger是什么时候触发
    ]
  },
  rows: [],
  ids: []
})

const formRef = ref()

const load=() =>{
  request.get('user/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      username: data.username,
      name: data.name
    }
  }).then(res =>{
    if(res.code === '200'){
      data.total=res.data.total
      data.tableData=res.data.list
    }else{
      ElMessage.error(res.msg)
    }
  })
}

load()

const reset = () => {
  data.name = null
  data.username = null
  load()
}

const handleAdd = () =>{
  data.formVisible = true
  data.form = {}
}
const add = () =>{
  formRef.value.validate((valid) => {
    if(valid){//验证通过
      request.post('user/add',data.form).then(res => {
        if(res.code === '200'){
          ElMessage.success('新增成功')
          data.formVisible = false
          load()
        }else{
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

const update =()=>{
  formRef.value.validate((valid) => {
    if(valid){//验证通过
      request.put('user/update',data.form).then(res => {
        if(res.code === '200'){
          ElMessage.success('修改成功')
          data.formVisible = false
          load()
        }else{
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

const save = () => {
  data.form.id ? update() : add()
}

const del =(id)=>{
  ElMessageBox.confirm('删除后无法恢复，确认删除改行数据吗','删除确认',{type:"warning"}).then(res =>{
    request.delete('user/delete/'+id).then(res => {
      if(res.code === '200'){
        ElMessage.success('删除成功')
        load()
      }else{
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {})
}

const handleFileSuccess=(res)=>{
  data.form.avatar = res.data
}

const handleSelectionChange = (rows) => {//实际选择的数组
  data.rows = rows
  data.ids = data.rows.map(v => v.id)
}

const deleteBatch = () =>{
  if(data.rows.length === 0){
    ElMessage.warning('请选择数据')
    return
  }

  ElMessageBox.confirm('删除后无法恢复，确认删除改行数据吗','删除确认',{type:"warning"}).then(res =>{
    request.delete('user/deleteBatch',{data: data.rows}).then(res => {
      if(res.code === '200'){
        ElMessage.success('批量删除成功')
        load()
      }else{
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {})
}

const exportData = () => {
  let idsStr = data.ids.join(",")  // 把数组转换成  字符串  [1,2,3]  ->  "1,2,3"
  let url = `http://localhost:9090/user/export?username=${data.username === null ? '' : data.username}`
      + `&name=${data.name === null ? '' : data.name}`
      + `&ids=${idsStr}`
  window.open(url)
}

const handleImportSuccess = (res) => {
  if (res.code === '200') {
    ElMessage.success('批量导入数据成功')
    load()
  } else {
    ElMessage.error(res.msg)
  }
}

</script>
