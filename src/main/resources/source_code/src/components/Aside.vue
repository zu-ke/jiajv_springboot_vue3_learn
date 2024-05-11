<template>
  <!--解读：内容部分的侧边栏部分-->
  <div>
    <el-row class="tac">
      <el-col :span="12">
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            style="width: max-content"
        >
          <el-menu-item index="1">
            <el-icon>
              <icon-menu/>
            </el-icon>
            <span>Navigator Two</span>
          </el-menu-item>

          <el-menu-item index="2">
            <el-icon>
              <document/>
            </el-icon>
            <span>Navigator Three</span>
          </el-menu-item>

          <el-menu-item index="3">
            <el-icon>
              <setting/>
            </el-icon>
            <span>Navigator Four</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>
