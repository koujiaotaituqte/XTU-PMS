<template>
  <div>
    <div class="card" style="margin-bottom: 5px;">
      <!-- 新增房间号和房型ID搜索 -->
      <el-input v-model="data.roomId" placeholder="房间号" clearable style="width: 200px; margin-right: 5px;"></el-input>
      <el-input v-model="data.roomTypeId" placeholder="房型ID" clearable style="width: 200px; margin-right: 5px;"></el-input>
      <el-select v-model="data.status" placeholder="状态" clearable style="width: 120px; margin-right: 5px;">
        <el-option label="空闲" :value="0" />
        <el-option label="使用中" :value="1" />
      </el-select>
      <el-button type="primary" @click="load">查询</el-button>
    </div>

    <div class="card">
      <el-table :data="data.tableData" style="width: 100%">
        <el-table-column prop="id" label="房间号" />
        <el-table-column prop="roomtypeId" label="房型ID" />
        <el-table-column label="状态">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'success' : 'danger'">
              {{ scope.row.status === 0 ? '空闲' : '使用中' }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request.js";

const data = reactive({
  roomId: null,      // 新增
  roomTypeId: null,  // 新增
  status: null,
  tableData: []
})

const load = () => {
  request.get('/room/findAll', {
    params: {
      id:data.roomId,
      roomtypeId:data.roomTypeId,
      status: data.status
    }
  }).then(res => {
    data.tableData = res.data || []
  })
}

load()
</script>