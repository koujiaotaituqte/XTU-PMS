<template>
  <div>
    <div class="card">
      <div class="notice-title">系统公告</div>
      <el-timeline class="timeline-container">
        <el-timeline-item
            v-for="item in data.noticeData"
            :key="item.time"
            :timestamp="item.time"
            placement="top"
            class="timeline-item"
        >
          <div class="timeline-content">
            <h4 class="timeline-title">{{ item.title }}</h4>
            <p class="timeline-text">{{ item.content }}</p>
          </div>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script setup>
import { Search } from "@element-plus/icons-vue";
import { reactive } from "vue";
import request from "@/utils/request.js";
import { ElMessage } from "element-plus";

const data = reactive({
  user: JSON.parse(localStorage.getItem("code_user") || "{}"),
  noticeData: [],
});

const loadNotice = () => {
  request.get("notice/selectAll").then((res) => {
    if (res.code === "200") {
      data.noticeData = res.data;
      if (data.noticeData.length > 3) {
        data.noticeData = data.noticeData.slice(0, 3);
      }
    } else {
      ElMessage.error(res.msg);
    }
  });
};

loadNotice();
</script>

<style scoped>
.card {
  margin-top: 20px;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.notice-title {
  font-size: 20px;
  font-weight: 600;
  margin: 10px 0 20px;
  color: #333;
}

.timeline-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 0 10px;
}

.timeline-item {
  :deep(.el-timeline-item__node) {
    background-color: #409eff;
    width: 12px;
    height: 12px;
  }
  :deep(.el-timeline-item__tail) {
    background-color: #e4e7ed;
  }
}

.timeline-content {
  background: #f5f7fa;
  padding: 12px 16px;
  border-radius: 4px;
  margin-bottom: 16px;
  transition: all 0.3s ease;
}

.timeline-content:hover {
  background: #ebf5ff;
  transform: translateX(5px);
}

.timeline-title {
  margin: 0 0 8px 0;
  color: #303133;
  font-size: 16px;
}

.timeline-text {
  margin: 0;
  color: #606266;
  font-size: 14px;
  line-height: 1.5;
}
</style>