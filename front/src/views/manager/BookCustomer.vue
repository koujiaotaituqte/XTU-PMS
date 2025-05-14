<template>
  <div>
    <div class="card" style="margin-bottom: 5px;">
      <el-input clearable @clear='load' style="margin-right: 5px;width: 260px;" v-model="data.userId" placeholder="用户ID" :prefix-icon="Search"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>

    <div class="card">
      <el-table :data="data.tableData" style="width: 100%" :header-cell-style="{color: 'black'}">
        <el-table-column prop="id" label="预定ID" />
        <el-table-column prop="roomtypeId" label="房型ID" />
        <el-table-column prop="userId" label="用户ID" /> <!-- 新增用户ID列 -->
        <el-table-column prop="startTime" label="开始时间" />
        <el-table-column prop="endTime" label="结束时间" />
        <el-table-column label="操作" width="120">
          <template #default="scope">
            <el-button type="success" @click="assignRoom(scope.row)">分配房间</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分配房间对话框 -->
    <el-dialog v-model="data.assignVisible" title="分配房间" width="30%">
      <el-select v-model="data.selectedRoom" placeholder="请选择房间">
        <el-option v-for="room in data.roomList" :key="room.id" :label="`房间 ${room.id}`" :value="room.id" />
      </el-select>
      <template #footer>
        <span>
          <el-button @click="data.assignVisible = false">取消</el-button>
          <el-button type="primary" @click="confirmAssign">确认分配</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import { ElMessage } from "element-plus";
import request from "@/utils/request.js";

const data = reactive({
  userId: null,
  tableData: [],
  assignVisible: false,
  selectedRoom: null,
  roomList: [],
  currentBooking: null
})

// 加载预定数据
const load = () => {
  request.get('/bookroom/selectPage', {
    params: {
      userId: data.userId
    }
  }).then(res => {
    data.tableData = res.data?.list || []
  })
}

// 分配房间操作
const assignRoom = (row) => {
  data.currentBooking = row
  // 加载可用房间
  request.get('/room', {params: {status: 0, roomtypeId: row.roomtypeId}}).then(res => {
    data.roomList = res.data || []
    data.assignVisible = true
  })
}

// 确认分配
const confirmAssign = () => {
  if (!data.selectedRoom) {
    ElMessage.warning('请选择房间');
    return;
  }
  // 获取当前预订的用户ID
  const userId = data.currentBooking.userId;
  const roomtypeId = data.currentBooking.roomtypeId; // 关键：新增 roomtypeId
  // 更新房间状态并关联用户ID
  request.put('/room/assign', {
    roomId: data.selectedRoom,
    userId: userId,
    roomtypeId: roomtypeId // 新增参数
  }).then(() => {
    // 可选：更新预订记录的 room_id（如需要）
    request.put('/bookroom/update', {
      id: data.currentBooking.id,
      roomId: data.selectedRoom
    }).then(() => {
      ElMessage.success('分配成功');
      data.assignVisible = false;
      load();
    });
  });
};

const reset = () => {
  data.userId = null
  load()
}

load()
</script>