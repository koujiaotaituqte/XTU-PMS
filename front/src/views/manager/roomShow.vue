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
        <el-table-column prop="description" label="房间描述" />
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 新增分页组件 -->
    <div class="card" style="margin-top: 20px;">
      <el-pagination
          v-model:current-page="data.pageNum"
          v-model:page-size="data.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[5, 10, 20, 50]"
          :total="data.total"
          @size-change="load"
          @current-change="load"
      />
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
      </el-form>
      <el-form-item label="房间描述">
        <el-input
            v-model="data.form.description"
            type="textarea"
            :rows="3"
            placeholder="请输入房间描述"
        />
      </el-form-item>
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
  // 新增分页相关状态
  pageNum: 1,
  pageSize: 10,
  total: 0,
  tableData: [],
  form: {},
  formVisible: false
})

const load = () => {
  request.get('/roomtype/selectPage', {
    params: {
      title: data.searchTitle,
      pageNum: data.pageNum,  // 新增分页参数
      pageSize: data.pageSize  // 新增分页参数
    }
  }).then(res => {
    // 假设后端返回格式为 { total: 100, list: [] }
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
  const isUpdate = !!data.form.id; // 判断是否为更新操作
  const url = isUpdate ? '/roomtype/update' : '/roomtype/add';
  const method = isUpdate ? 'put' : 'post'; // 动态选择方法

  // 使用通用请求方法，区分PUT和POST
  request({
    url: url,
    method: method, // 指定HTTP方法
    data: data.form
  }).then(() => {
    ElMessage.success('操作成功');
    data.formVisible = false;
    load(); // 加载最新数据（包含分页）
  }).catch((error) => {
    // 捕获并显示错误信息
    ElMessage.error('操作失败: ' + (error.response?.data?.message || '未知错误'));
  });
};

const reset = () => {
  data.searchTitle = '';
  data.pageNum = 1; // 重置后回到第一页
  load();
}

load()
</script>