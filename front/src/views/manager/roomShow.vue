<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.searchTitle" placeholder="房型名称" style="width: 200px; margin-right: 10px;"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
      <el-button type="primary" @click="handleAdd" style="float: right;">新增房型</el-button>
    </div>

    <!-- 表格展示区域 -->
    <div class="card">
      <el-table :data="data.tableData">
        <!-- 增加图片展示列 -->
        <el-table-column label="房间图片">
          <template #default="scope">
            <el-image
                v-if="scope.row.avatar"
                :src="scope.row.avatar"
                :preview-src-list="[scope.row.avatar]"
                :preview-teleported="true"
                style="width: 40px; height: 40px; display: block"
            >
              <template #error>
                <div class="image-error">
                  <el-icon><Picture /></el-icon>
                </div>
              </template>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="房型id" />
        <el-table-column prop="title" label="房型名称" />
        <el-table-column prop="money" label="价格" />
        <el-table-column prop="description" label="房间描述" />
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 编辑对话框 -->
    <el-dialog v-model="data.formVisible" :title="data.form.id ? '编辑房型' : '新增房型'" width="30%">
      <el-form :model="data.form">
        <el-form-item label="房型名称">
          <el-input v-model="data.form.title" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input-number v-model="data.form.money" :min="0" />
        </el-form-item>
        <el-form-item label="房间描述">
          <el-input
              v-model="data.form.description"
              type="textarea"
              :rows="3"
              placeholder="请输入房间描述"
          />
        </el-form-item>
        <!-- 增加图片上传 -->
        <el-form-item label="房间图片">
          <div style="display: flex; align-items: center; gap: 10px;">
            <el-upload
                action="http://localhost:9090/files/upload"
                :on-success="handleFileSuccess"
                :headers="{ token: data.user.token }"
                :show-file-list="false"
            >
              <el-button type="primary">上传图片</el-button>
            </el-upload>
            <el-image
                v-if="data.form.avatar"
                :src="data.form.avatar"
                style="width: 40px; height: 40px; border-radius: 5px"
            />
          </div>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="data.formVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import request from "@/utils/request.js";
import { ElMessage, ElMessageBox } from "element-plus";
import { Picture } from "@element-plus/icons-vue";

const data = reactive({
  searchTitle: '',
  pageNum: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  form: {},
  formVisible: false,
  user: JSON.parse(localStorage.getItem('code_user') || '{}') // 假设用户信息存储在localStorage中
})

const load = () => {
  request.get('/roomtype/selectPage', {
    params: {
      title: data.searchTitle,
      pageNum: data.pageNum,
      pageSize: data.pageSize
    }
  }).then(res => {
    data.total = res.data.total;
    data.tableData = res.data.list || [];
  })
}

const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

const handleEdit = (row) => {
  data.form = { ...row }
  data.formVisible = true
}

const save = () => {
  const isUpdate = !!data.form.id;
  const url = isUpdate ? '/roomtype/update' : '/roomtype/add';
  const method = isUpdate ? 'put' : 'post';

  request({
    url: url,
    method: method,
    data: data.form
  }).then(() => {
    ElMessage.success('操作成功');
    data.formVisible = false;
    load();
  }).catch((error) => {
    ElMessage.error('操作失败: ' + (error.response?.data?.message || '未知错误'));
  });
};

const reset = () => {
  data.searchTitle = '';
  data.pageNum = 1;
  load();
}

// 图片上传成功处理
const handleFileSuccess = (res) => {
  if (res.code === '200') {
    data.form.avatar = res.data;
    ElMessage.success('图片上传成功');
  } else {
    ElMessage.error(res.msg);
  }
};

// 初始化加载
load();
</script>

<style>
.image-error {
  width: 40px;
  height: 40px;
  border-radius: 5px;
  background: #f5f5f5;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #999;
}
</style>