

<template>
  <div class="card" style="margin-bottom: 5px;">
    <el-input clearable style="margin-right: 5px;width: 260px;" v-model="data.name" placeholder="请输入名称查询" ></el-input>
    <el-button type="primary" @click="load">查 询</el-button>
  </div>

  <div class="card" style="margin-bottom: 5px;">
    <el-button type="danger">批量删除</el-button>
    <el-button type="info">新增</el-button>
    <el-button type="primary">批量导入</el-button>
    <el-button type="success">批量导出</el-button>
  </div>

  <div class="card" style="margin-bottom: 5px;">
    <el-table :data="data.tableData" style="width: 100%" :header-cell-style="{color: 'black'}">
    <el-table-column type="selection" width="55"/>
    <el-table-column prop="username" label="账号"  />
    <el-table-column prop="name" label="名称" />
    <el-table-column prop="phone" label="电话" />
    <el-table-column prop="email" label="邮箱" />
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
</template>


<script setup>
import { reactive } from 'vue';
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";




const data = reactive({
  name: null,
  pageNum: 1,
  pageSize: 5,
  total: 0,
  tableData: []
})

const load=() =>{
  request.get('admin/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name
    }
  }).then(res =>{
    if(res.code === '200'){
      data.total=res.data.total
      data.tableData=res.data.list
    }else{
      ElMessage.log(res.msg)
    }
  })
}

load()
</script>