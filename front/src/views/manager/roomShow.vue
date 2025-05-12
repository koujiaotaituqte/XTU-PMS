<template>
  <div>
    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.searchTitle" placeholder="房型名称" style="width: 200px; margin-right: 10px;"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
      <el-button type="primary" @click="handleAdd" style="float: right;">新增房型</el-button>
    </div>

    <div class="card">
      <el-table :data="data.tableData">
        <el-table-column prop="id" label="房型id" />
        <el-table-column prop="title" label="房型名称" />
        <el-table-column prop="money" label="价格" />
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
        <!-- 新增时显示ID输入框 -->
        <el-form-item v-if="!data.form.id" label="房型ID" prop="id" :rules="[{ required: true, message: 'ID不能为空', trigger: 'blur' }]">
          <el-input-number v-model="data.form.id" :min="1" controls-position="right" />
        </el-form-item>

        <el-form-item label="房型名称">
          <el-input v-model="data.form.title" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input-number v-model="data.form.money" :min="0" />
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
import { reactive } from "vue";
import { ElMessage } from "element-plus";
import request from "@/utils/request.js";

const data = reactive({
  searchTitle: '',
  tableData: [],
  form: {},
  formVisible: false
})

const load = () => {
  request.get('/roomtype/selectPage', {
    params: {
      title: data.searchTitle
    }
  }).then(res => {
    data.tableData = res.data?.list || []
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
  if (!data.form.id) { // 新增时必须填写ID
    ElMessage.warning('房型ID不能为空')
    return
  }
  const url = data.form.id ? '/roomtype/update' : '/roomtype/add'
  request.post(url, data.form).then(() => {
    ElMessage.success('操作成功')
    data.formVisible = false
    load()
  })
}

const reset = () => {
  data.searchTitle = ''
  load()
}

load()
</script>