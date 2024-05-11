<template>
  <div>
    <!--头部-->
    <!--解读：引用组件顶部导航栏-->
    <Header/>

    <!--内容-->
    <div style="display: flex">
      <!--解读：引用组件内容侧边栏-->
      <Aside/>
      <!--解读：展示的内容，从views/xxx 比如HomeView.vue组件来的（通过路由机制）-->
      <router-view style="flex: 1"></router-view>
    </div>
  </div>
</template>
<style>
</style>
<script>
/*解读：导入组件*/
import Header from "@/components/Header.vue"
import Aside from "@/components/Aside.vue"
/*解读：导出组件，导出后才可以在当前页面使用*/
export default {
  name: "Layout",
  components: {
    Header,
    Aside
  }
}
</script>
