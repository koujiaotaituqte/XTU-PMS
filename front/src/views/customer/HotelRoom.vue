<template>
  <div>
    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.searchTitle" placeholder="房型名称" style="width: 200px; margin-right: 10px;"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>

    <!-- 房间展示区域 -->
    <div class="room-display" style="margin-top: 20px;">
      <div class="room-cards">
        <h2>可用房间</h2>
        <el-card
            v-for="item in data.tableData"
            :key="item.id"
            class="item-card"
            style="width: 100%; max-width: 400px; margin-bottom: 20px; margin-right: 20px; display: inline-block;"
        >
          <!-- 图片展示区域-->
          <img
              :src="item.avatar"
              :alt="item.title"
              style="width: 100%; height: 200px; object-fit: cover;"
          />

          <!-- 标题区 -->
          <template #header>
            <div
                style="display: flex; justify-content: space-between; align-items: center; font-size: 18px; font-weight: bold;"
            >
              <span>{{ item.title }}</span>
              <span style="color: #043040; font-size: 16px;font-weight: bolder">¥{{ item.money }}/晚</span>
            </div>
          </template>

          <!-- 内容区 -->
          <div style="color: #666; line-height: 1.6; padding: 0 5px; margin-bottom: 10px; min-height: 60px; font-weight: bold; word-wrap: break-word; height: 80px; overflow-y: auto;">
            {{ item.description }}
          </div>

          <!-- 日期选择和预订区域 -->
          <div style="margin-top: 15px;">
            <div style="display: flex; gap: 10px; margin-bottom: 10px;">
              <el-date-picker
                  v-model="item.checkInDate"
                  type="date"
                  placeholder="入住日期"
                  :disabled-date="disabledCheckInDate"
                  @change="CntDay(item)"
                  size="small"
                  style="flex: 1;"
              />
              <el-date-picker
                  v-model="item.checkOutDate"
                  type="date"
                  placeholder="离店日期"
                  :disabled-date="(time) => disabledCheckOutDate(time, item)"
                  @change="CntDay(item)"
                  size="small"
                  style="flex: 1;"
              />
            </div>
            <div v-if="item.stayDays > 0" style="text-align: center; font-size: 14px;font-weight: bolder; color: #333; padding: 5px 0;">
              共 {{ item.stayDays }} 晚，总价: ¥{{ totalPrice(item) }}
            </div>
            <div v-else style="text-align: center; font-size: 14px; color: #333; padding: 5px 0;">
              <br>
            </div>
            <div style="display: flex; gap: 10px; margin-top: 10px;width: 50px">
              <el-button
                  type="primary"
                  size="small"
                  @click="handleBooking(item)"
                  :disabled="!item.canBook"
                  style="flex: 1;"
              >
                立即预订
              </el-button>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, computed } from "vue";
import request from "@/utils/request.js";
import { ElMessage } from "element-plus";

const data = reactive({
  searchTitle: '',
  tableData: [],
  form: {}
})

// 初始化加载数据
const load = () => {
  request.get('/roomtype/selectPage', {
    params: {
      title: data.searchTitle
    }
  }).then(res => {
    // 为每个房间添加独立的状态
    data.tableData = res.data.list.map(item => ({
      ...item,
      checkInDate: '',
      checkOutDate: '',
      stayDays: 0,
      canBook: false
    })) || [];
  })
}

const reset = () => {
  data.searchTitle = '';
  load();
}

// 计算总价
const totalPrice = (item) => {
  return item.stayDays * (item.money || 0)
}

// 计算天数
const CntDay = (item) => {
  if (item.checkInDate && item.checkOutDate) {
    const diffTime = Math.abs(item.checkOutDate.getTime() - item.checkInDate.getTime())
    item.stayDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
    item.canBook = true
  } else {
    item.stayDays = 0
    item.canBook = false
  }
}

// 禁用开始日期
const disabledCheckInDate = (time) => {
  return time.getTime() < Date.now() - 86400000 // 86400000 = 24*60*60*1000
}

// 禁用结束日期
const disabledCheckOutDate = (time, item) => {
  if (!item.checkInDate) return true
  return time.getTime() <= item.checkInDate.getTime()
}

const handleBooking = (item) => {
  if (!item.canBook) {
    ElMessage.warning('请选择有效的日期范围');
    return;
  }

  const user = JSON.parse(localStorage.getItem('code_user') || '{}');

  const bookingData = {
    roomtypeId: item.id,
    userId: user.id,
    startTime: item.checkInDate,
    endTime: item.checkOutDate,
    day: item.stayDays,
  };

  request.post('/bookroom/add', bookingData)
      .then(res => {
        if (res.code === '200') {
          ElMessage.success('预订成功');
          // 重置当前房间的日期
          item.checkInDate = '';
          item.checkOutDate = '';
          item.stayDays = 0;
          item.canBook = false;
        } else {
          ElMessage.error(res.msg)
        }
      })
};

load()
</script>