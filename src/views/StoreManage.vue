<template>
  <div class="manage">
  <div class="manage_home">
    <Header></Header>
        <div style="padding: 10px" class="table_name">
    <!--    功能区域-->

    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 40%" clearable></el-input>
      <el-button type="primary" size="large" style="margin-left: 10px" @click="load">查询</el-button>
      <el-button type="primary" size="large" style="margin-left: 20px" @click="dialogFormVisible = true">新增</el-button>
    </div>


    <el-dialog v-model="dialogFormVisible" title="上传文件">
      <el-form
        class="manage_form"
        ref="form"
        :model="sizeForm"
        label-width="auto"
        size="large"
      >
        <el-form-item label="上传人">
          <el-input v-model="sizeForm.author" />
        </el-form-item> 
        <el-form-item label="文件格式">
          <el-select
            v-model="sizeForm.format"
            placeholder="选择你要上传的数据格式"
          >
            <el-option label="图片文件" value="图片文件" />
            <el-option label="Excel" value="Excel" />
            <el-option label="Word" value="Word" />
            <el-option label="矢量文件" value="矢量文件" />
            <el-option label="文本资料" value="文本资料" />
          </el-select>
        </el-form-item>
        <el-form-item label="上传日期">
            <el-input v-model="sizeForm.date"  />
        </el-form-item> 
        <el-form-item label="文件描述" prop="content">
          <el-input v-model="sizeForm.content" type="textarea" />
        </el-form-item>   
        <el-form-item label="选择上传的文件">
      <el-upload  
       ref="upload"
      :action="filesUploadUrl" 
      :on-success="fileUploadSuccess"    
      >
      <el-button type="primary" :v-model="sizeForm.filename">选取文件</el-button>
        </el-upload>
        </el-form-item>
        <el-form-item >
          <el-button type="primary" @click="upload">上传</el-button>
          <el-button type="primary" @click="Reset">重置</el-button>
        </el-form-item>
  </el-form>


    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>


  
    <div class="table-wrapper">
    <el-table
        v-loading="loading"
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="ID"
          sortable
      >
      </el-table-column>
      
      <el-table-column
          prop="author"
          label="作者">
      </el-table-column>
      <el-table-column
          prop="format"
          label="文件类型">
      </el-table-column>
      <el-table-column
          prop="date"
          label="上传时间">
      </el-table-column>
      <el-table-column
          prop="filename"
          label="文件路径">
      </el-table-column>
      <el-table-column
          prop="content"
          label="文件描述">
      </el-table-column>
      <el-table-column label="操作" fit>
        <template #default="scope">
          <el-button type="primary" size="mini" @click="download(scope.row.filename)">下载</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    </div>

        <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    </div> 
  </div>
</div>
</template>

<script>
import request from "@/utils/request";
import Header from '../components/Header.vue'

export default {
    components: {
    Header,
},
    data() {
      return {
          sizeForm: {},
          filesUploadUrl: "http://localhost:9999/files/upload",
          tableData: [],
          search: '',
          currentPage: 1,
          pageSize: 5,
          total: 0,
          loading: true,
          dialogFormVisible: false,
          
      };
    },
    created() {
        this.sizeForm.date = new Date();
        this.load()
    },
    methods: {
       
        load() {
            this.loading = true,
                request.get("/file", {
                    params: {
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search,
                }
                }).then(res => {
                    this.loading = false,
                    this.tableData = res.data.records
                    this.total = res.data.total
            })
        },
         upload() {        
                request.post("/file", this.sizeForm).then(res => {
                    console.log(res)
                    console.log(res.data)
                    console.log(222222222)
                    //this.sizeForm.filename = res.data
                    if (res.code === '0')
                    {
                        console.log(3333333333333)
                    //this.sizeForm.filename = res.data
                    this.$message({
                        type: "success",
                        message: "新增成功！",
                    })

                } else {
                    this.$message({
                        type: "error",
                        message: res.msg
                    })
                    }
                    this.load()
                
                })   
        },
        handleDelete(id) {
            console.log(id)
            request.delete("/file/" + id).then(res => {
                if (res.code === '0')
                {
                    this.$message({
                        type: "success",
                        message: "删除成功！",
                    })

                } else {
                    this.$message({
                        type: "error",
                        message: res.msg
                    })
                }
                this.load()
            })
        },
        handleSizeChange(pageSize) {
            this.pageSize = pageSize,
            this.load()
        },
        handleCurrentChange(pageNum) {
            this.currentPage = pageNum,
            this.load()
        },
        Reset() {
            console.log('cancel!');
            this.sizeForm = {}
        },
        fileUploadSuccess(res) {
            console.log(res)
            this.sizeForm.filename = res.data;
      },
      download(filename) {
        // var elemIF = document.createElement("iframe")
        // elemIF.src
        
        console.log(filename)
        // filename = filename.slice(28)
        // console.log(filename)
        request.get("/files/" + filename).then(res => {
        // console.log(res)
        })
        window.open(filename)
        
      }
  }
    
  };
</script>

<style scoped>
  .manage{
    width: 100%;
    height: 100%;
    padding: 16px 16px 0 16px;
    top: 0px;
    left: 0px;
    bottom: 0px;
    right: 0px;
    position: fixed;
    /* z-index: -1;    
    background-image: url("../assets/pageBg.png");
    background-size: cover;
    background-position: center center; */
  }
.manage_form{
    width: 100%;
    height: 100%;
    margin-top: 90px;
    margin-left: 10px;
    
}
.table_name{
    position: absolute;
    width: 100%;
    height: 100%;
    /* left: 520px; */
    top: 100px;
}

</style>