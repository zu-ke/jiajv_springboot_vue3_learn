<template>
  <!--解读：顶部导航栏-->
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px; padding-left: 30px;font-weight: bold;color: dodgerblue">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;">
      <el-dropdown>
        <span class="el-dropdown-link" style="height: 50px; line-height: 50px;">
          选项
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>Action 1</el-dropdown-item>
                <el-dropdown-item>Action 2</el-dropdown-item>
              </el-dropdown-menu>
            </template>
      </el-dropdown>
    </div>
  </div>
</template>
<style>
</style>
<script>
export default {
  name: "Header"
}
</script>