<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.searchTitle" placeholder="房型名称" style="width: 200px; margin-right: 10px;"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>

    <!-- 房间展示区域 -->
    <div class="room-display" style="margin-top: 20px;">
      <h2>可用房间</h2>
      <div class="room-cards">
        <el-card
            v-for="item in data.tableData"
            :key="item.id"
            class="item-card"
            style="width: 100%; max-width: 400px; margin-bottom: 20px; margin-right: 20px; display: inline-block;"
        >
          <!-- 图片展示 - 添加alt属性 -->
          <img
              :src="item.avatar || 'https://via.placeholder.com/400x200'"
              :alt="item.title || '酒店房间图片'"
              style="width: 100%; height: 200px; object-fit: cover;"
              onerror="this.src='https://via.placeholder.com/400x200'"
          />

          <!-- 标题区 -->
          <template #header>
            <div
                style="display: flex; justify-content: space-between; align-items: center; font-size: 18px; font-weight: bold;"
            >
              <span>{{ item.title || '默认房间标题' }}</span>
              <span style="color: #f56c6c; font-size: 16px;">¥{{ item.money || '0' }}/晚</span>
            </div>
          </template>

          <!-- 内容区 -->
          <div
              style="color: #666; line-height: 1.6; padding: 0 5px; margin-bottom: 10px; min-height: 60px;"
          >
            {{ item.description || '这里是房间的详细描述信息，默认描述内容...' }}
          </div>

          <!-- 日期选择和预订区域 -->
          <div style="margin-top: 15px;">
            <div style="display: flex; gap: 10px; margin-bottom: 10px;">
              <el-date-picker
                  v-model="checkInDate"
                  type="date"
                  placeholder="入住日期"
                  :disabled-date="disabledCheckInDate"
                  @change="calculateStayDays"
                  size="small"
                  style="flex: 1;"
              />
              <el-date-picker
                  v-model="checkOutDate"
                  type="date"
                  placeholder="离店日期"
                  :disabled-date="disabledCheckOutDate"
                  @change="calculateStayDays"
                  size="small"
                  style="flex: 1;"
              />
            </div>
            <div v-if="stayDays > 0" style="text-align: center; font-size: 14px; color: #333; padding: 5px 0;">
              共 {{ stayDays }} 晚，总价: ¥{{ totalPrice(item.money) }}
            </div>
            <div style="display: flex; gap: 10px; margin-top: 10px;width: 50px">
              <el-button
                  type="primary"
                  size="small"
                  @click="handleBooking(item)"
                  :disabled="!canBook"
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
import {ElMessage} from "element-plus";

const data = reactive({
  searchTitle: '',
  tableData: []
})

const checkInDate = ref('')
const checkOutDate = ref('')
const stayDays = ref(0)

const load = () => {
  request.get('/roomtype/selectPage', {
    params: {
      title: data.searchTitle
    }
  }).then(res => {
    data.tableData = res.data.list || [];
  })
}

const reset = () => {
  data.searchTitle = '';
  load();
}

// 计算总价
const totalPrice = (price) => {
  return stayDays.value * (price || 0)
}

// 是否可以预订
const canBook = computed(() => {
  return stayDays.value > 0
})

// 计算住宿天数
const calculateStayDays = () => {
  if (checkInDate.value && checkOutDate.value) {
    const diffTime = Math.abs(checkOutDate.value.getTime() - checkInDate.value.getTime())
    stayDays.value = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
  } else {
    stayDays.value = 0
  }
}

// 禁用开始日期（只能选择今天及之后的日期）
const disabledCheckInDate = (time) => {
  return time.getTime() < Date.now() - 86400000 // 86400000 = 24*60*60*1000
}

// 禁用结束日期（不能早于开始日期）
const disabledCheckOutDate = (time) => {
  if (!checkInDate.value) return true
  return time.getTime() <= checkInDate.value.getTime()
}

// 处理预订
const handleBooking = (item) => {
  if (!canBook.value) {
    ElMessage.warning('请选择有效的日期范围')
    return
  }

  // 这里可以替换为实际的预订逻辑
  console.log('预订信息:', {
    roomId: item.id,
    checkInDate: checkInDate.value,
    checkOutDate: checkOutDate.value,
    stayDays: stayDays.value,
    totalPrice: totalPrice(item.money)
  })

  ElMessage.success('预订成功！')
}




load()
</script>