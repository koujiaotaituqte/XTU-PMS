<template>
  <div>
    <!-- 新增房间表单 -->
    <div class="card" style="margin-bottom: 15px;">
      <el-form :inline="true" :model="formData" :rules="formRules" ref="addForm">
        <el-form-item label="房间号" prop="id">
          <el-input
              v-model.number="formData.id"
              placeholder="请输入房间号"
              style="width: 200px; margin-right: 10px;"
              clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="房型ID" prop="roomtypeId">
          <el-input
              v-model.number="formData.roomtypeId"
              placeholder="请输入房型ID"
              style="width: 200px; margin-right: 10px;"
              clearable
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitAddForm">新增房间</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 原有查询表单和表格 -->
    <div class="card" style="margin-bottom: 5px;">
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
        <el-table-column prop="roomtypeTitle" label="房型名称" />
        <el-table-column prop="userId" label="用户ID" />
        <el-table-column label="状态">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'success' : 'danger'">
              {{ scope.row.status === 0 ? '空闲' : '使用中' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button type="primary" size="small" @click="openEditDialog(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 编辑弹窗 -->
    <el-dialog v-model="editDialogVisible" title="编辑房间" width="30%">
      <el-form :model="editFormData" :rules="formRules" ref="editForm">
        <el-form-item label="房间号" prop="id">
          <el-input v-model.number="editFormData.id" placeholder="请输入房间号" clearable />
        </el-form-item>
        <el-form-item label="房型ID" prop="roomtypeId">
          <el-input v-model.number="editFormData.roomtypeId" placeholder="请输入房型ID" clearable />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="editFormData.status" placeholder="请选择状态">
            <el-option label="空闲" :value="0" />
            <el-option label="使用中" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="入住人ID" prop="userId">
          <el-input v-model.number="editFormData.userId" placeholder="请输入用户ID" clearable />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitEditForm">确认</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import request from "@/utils/request.js";
import { ElMessage, ElMessageBox } from "element-plus";

const data = reactive({
  roomId: null,
  roomTypeId: null,
  status: null,
  tableData: []
});

// 新增房间表单相关
const addForm = ref(null);
const formData = reactive({
  id: null,
  roomtypeId: null,
  status: 0
});

// 编辑功能相关
const editDialogVisible = ref(false);
const editForm = ref(null);
const editFormData = reactive({
  id: null,
  roomtypeId: null,
  status: 0,
  userId: null
});

const formRules = reactive({
  id: [
    { required: true, message: '房间号不能为空', trigger: 'blur' },
    { type: 'number', message: '房间号必须为数字' }
  ],
  roomtypeId: [
    { required: true, message: '房型ID不能为空', trigger: 'blur' },
    { type: 'number', message: '房型ID必须为数字' }
  ],
  status: [
    { required: true, message: '状态不能为空', trigger: 'change' }
  ]
});

// 提交新增表单
const submitAddForm = () => {
  addForm.value.validate(valid => {
    if (valid) {
      request.post('/room', formData)
          .then(() => {
            ElMessage.success('新增成功');
            load();
            addForm.value.resetFields();
          })
          .catch(err => {
            ElMessage.error(err.response?.data?.message || '新增失败');
          });
    }
  });
};

// 打开编辑弹窗
const openEditDialog = (row) => {
  Object.assign(editFormData, row);
  editDialogVisible.value = true;
};

// 提交编辑表单
const submitEditForm = () => {
  editForm.value.validate(valid => {
    if (valid) {
      request.put('/room', editFormData)
          .then(() => {
            ElMessage.success('修改成功');
            load();
            editDialogVisible.value = false;
          })
          .catch(err => {
            ElMessage.error(err.response?.data?.message || '修改失败');
          });
    }
  });
};

// 删除操作
const handleDelete = (id) => {
  ElMessageBox.confirm('确认删除该房间吗？', '警告', { type: 'warning' })
      .then(() => {
        request.delete(`/room/${id}`)
            .then(() => {
              ElMessage.success('删除成功');
              load();
            })
            .catch(err => {
              ElMessage.error(err.response?.data?.message || '删除失败');
            });
      })
      .catch(() => {});
};

// 加载表格数据
const load = () => {
  request.get('/room', {
    params: {
      id: data.roomId,
      roomtypeId: data.roomTypeId,
      status: data.status
    }
  }).then(res => {
    data.tableData = res.data || [];
  });
};

load();
</script>