

<template>
  <div class="card" style="margin-bottom: 5px;">
    <el-input style="margin-right: 5px;width: 260px;" v-model="data.name" placeholder="请输入名称查询" ></el-input>
    <el-button type="primary">查 询</el-button>
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
    <el-table-column prop="name" label="姓名" width="180" />
    <el-table-column prop="phone" label="电话" />
    <el-table-column prop="address" label="地址" width="180" />
  </el-table>
  </div>

  <div class="card">
    <el-pagination
      v-model:current-page="data.pageNum"
      :page-size="data.pageSize"
      layout="total, prev, pager, next"
      :total="data.total"
    />
  </div>
</template>


<script setup>
import { reactive } from 'vue';
import axios from "axios";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";



const data = reactive({
  name: null,
  pageNum: 1,
  pageSize: 99,
  total: 1,
  tableData: [
    {name: '张三',phone: '12345678910',address: '张家界'},
    {name: '李四',phone: '12345678910',address: '张家界'}
  ]
})

request.get('admin/selectAll').then(res =>{
  if(res.code !== '200'){
    ElMessage.log(res)
  }else{
    console.log(res)
  }
})

</script>