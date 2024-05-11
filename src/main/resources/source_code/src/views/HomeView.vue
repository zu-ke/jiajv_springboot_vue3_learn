<template>
  <div style="margin-left: 10px">
    <!--解读：按钮-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button>其他</el-button>
    </div>

    <!--解读：添加和编辑furn的表单-->
    <el-dialog
        v-model="dialogVisible"
        title="新增"
        width="30%"
        :before-close="handleClose"
    >
      <el-form ref="formRef" :model="furn" :rules="rules" label-width="120px">
        <el-form-item label="家居名" prop="name">
          <el-input v-model="furn.name" style="width: 80%"/>
          <span style="color: red">{{this.javaValidMsg.name}}</span>
        </el-form-item>

        <el-form-item label="厂商" prop="maker">
          <el-input v-model="furn.maker" style="width: 80%"/>
          <span style="color: red">{{this.javaValidMsg.maker}}</span>
        </el-form-item>

        <el-form-item label="价格" prop="price">
          <el-input v-model="furn.price" style="width: 80%"/>
          <span style="color: red">{{this.javaValidMsg.price}}</span>
        </el-form-item>

        <el-form-item label="销量" prop="sales">
          <el-input v-model="furn.sales" style="width: 80%"/>
          <span style="color: red">{{this.javaValidMsg.sales}}</span>
        </el-form-item>

        <el-form-item label="库存" prop="stock">
          <el-input v-model="furn.stock" style="width: 80%"/>
          <span style="color: red">{{this.javaValidMsg.stock}}</span>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="save">新增</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!--解读：引入搜索框-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 30%;"></el-input>
      <el-button @click="list" style="margin-left: 10px" type="primary">查询</el-button>
    </div>

    <!--解读：表格部分-->
    <el-table :data="tableData" height="500" style="width: 100%">
      <el-table-column sortable prop="id" label="ID"/>
      <el-table-column prop="name" label="家居名"/>
      <el-table-column prop="maker" label="厂家"/>
      <el-table-column prop="price" label="价格"/>
      <el-table-column prop="sales" label="销量"/>
      <el-table-column prop="stock" label="库存"/>

      <el-table-column fixed="right" label="操作" width="100">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>
          <!--解读：加入确认操作的确认框-->
          <el-popconfirm title="你确定删除吗?" @confirm="del(scope.row.id)">
            <template #reference>
              <el-button type="text">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--解读：分页-->
    <div>
      <el-pagination
          background
          layout="prev, pager, next,total,sizes,jumper"
          :total="total"
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,9]"
          :page-size=pageSize></el-pagination>
    </div>
  </div>
</template>

<script>
/*解读：引入工具类*/
import request from '@/utils/request'

export default {
  name: 'HomeView',
  data() {
    return {
      /*解读：后端表单校验信息*/
      javaValidMsg: {},
      /*解读：存放后端校验的信息*/
      serverValidErrors: {},
      /*解读：
      *   1.添加表单的校验规则
      *   2.这里可以写多个针对name等属性的校验规则
      * */
      rules: {
        name: [
          {required: true, message: "请输入家居名", trigger: "blur"}
        ],
        maker: [
          {required: true, message: "请输入制造商名", trigger: "blur"}
        ],
        price: [
          {required: true, message: "请输入价格", trigger: "blur"},
          {pattern: /^([1-9]\d*|0)(\.\d+)?$/, message: "请输入数字", trigger: "blur"}
        ],
        sales: [
          {required: true, message: "请输入销量", trigger: "blur"},
          {pattern: /^([1-9]\d*|0)?$/, message: "请输入整数", trigger: "blur"}
        ],
        stock: [
          {required: true, message: "请输入库存", trigger: "blur"},
          {pattern: /^([1-9]\d*|0)?$/, message: "请输入整数", trigger: "blur"}
        ]
      },
      total: 20,
      pageSize: 9, //每页显示
      currentPage: 1, //当前页
      dialogVisible: false,
      furn: {},
      tableData: [],
      search: ""
    }
  },
  mounted() { //在此方法中向后端请求参数
    this.list();
  },
  methods: {
    /*解读：处理修改currentPage*/
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      this.list(); //刷新家居列表
    },

    /*解读：处理修改pageSize*/
    handlePageSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.list(); //刷新家居列表
    },

    /*解读：普通方式查询所有数据*/
    list() {
      //request.get("http://localhost:8080/list").then(res => {
      //  this.tableData = res.data;
      //})
      request.get("http://localhost:8080/page2", {
        params: {
          pageSize: this.pageSize,
          pageNum: this.currentPage,
          search: this.search
        }
      }).then(res => {
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },

    /*解读：修改操作中的查询当前修改对象的属性*/
    handleEdit(row) {
      request.get("http://localhost:8080/getById?id=" + row.id).then(res => {
        this.furn = res.data;
        this.dialogVisible = true;
      });
    },

    /*解读：新增操作中打开对话框并且清空上一次遗留的数据*/
    add() {
      this.dialogVisible = true
      this.furn = {}
      /*解读：将上次前端表单验证的信息清空*/
      this.$nextTick(() => {
        this.$refs['formRef'].resetFields();
      });
      /*解读：将上次后端表单验证的信息清空*/
      this.javaValidMsg = {};
    },

    /*解读：根据furn是否有id判断是新增还是修改在、操作*/
    save() {
      if (this.furn.id) {
        /*解读：修改*/
        request.put("http://localhost:8080/update", this.furn).then(res => {
          if (res.code == "200") {
            /*解读：提示消息框*/
            this.$message({
              type: "success",
              message: "success"
            })
          } else {
            /*解读：提示消息框*/
            this.$message({
              type: "error",
              message: res.msg ? res.msg : "error"
            })
          }
          this.dialogVisible = false;
          this.list();
        })
      } else {
        /*解读：新增*/
        //request.post("http://localhost:8080/save", this.furn).then(res => {
        //  if (res.code == "200") {
        //    /*解读：提示消息框*/
        //    this.$message({
        //      type: "success",
        //      message: "success"
        //    })
        //  } else {
        //    /*解读：提示消息框*/
        //    this.$message({
        //      type: "error",
        //      message: res.msg ? res.msg : "error"
        //    })
        //  }
        //  this.dialogVisible = false;
        //  this.list();
        //})

        /*解读：新增，和表单的校验规则关联起来，如果表单校验没通过就不提交*/
        this.$refs['formRef'].validate(valid => {
          if (valid) {
            request.post("http://localhost:8080/save", this.furn).then(res => {
              if (res.code == "200") {
                /*解读：提示消息框*/
                this.$message({
                  type: "success",
                  message: "success"
                })
                this.dialogVisible = false;
                this.list();
              } else if (res.code === "1001"){
                this.javaValidMsg.name = res.data.name;
                this.javaValidMsg.maker = res.data.maker;
                this.javaValidMsg.price = res.data.price;
                this.javaValidMsg.sales = res.data.sales;
                this.javaValidMsg.stock = res.data.stock;

                /*解读：提示消息框*/
                this.$message({
                  type: "error",
                  message: res.msg ? res.msg : "error"
                })
              }
            })
          } else {
            this.$message({
              type: "error",
              message: "请正确填写表单"
            })
            return false;
          }
        })
      }
    },

    /*解读：删除数据请求*/
    del(id) {
      request.delete("http://localhost:8080/delById?id=" + id).then(res => {
        if (res.code == "200") {
          this.$message({
            type: "success",
            message: res.msg ? res.msg : "success"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg ? res.msg : "error"
          })
        }
        this.list();
      })
    }
  }
}
</script>
