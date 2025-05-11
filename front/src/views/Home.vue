<template>
  <div>
    <div class="card">
      欢迎使用本系统，这是酒店封面和粗略介绍
    </div>

    <div class="card" style="margin-top: 20px">
      <div style="font-size: 20px;margin-top: 10px;margin-bottom: 10px;">系统公告</div>
      <el-timeline style="max-width: 600px">
        <el-timeline-item :timestamp="item.time" placement="top" v-for="item in data.noticeData">
          <h4>{{ item.title }}</h4>
          <p>{{ item.content }}</p>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script setup>

import {Search} from "@element-plus/icons-vue";
import {reactive,ref} from "vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  user : JSON.parse(localStorage.getItem('code_user')||"{}"),
  noticeData: [],
})

const loadNotice =()=>{
  request.get('notice/selectAll').then(res=>{
    if(res.code==='200'){
      data.noticeData=res.data
      if(data.noticeData.length>3){
        data.noticeData = data.noticeData.slice(0,3)
      }
    }else{
      ElMessage.error(res.msg);
    }
  })
}

loadNotice()

</script>