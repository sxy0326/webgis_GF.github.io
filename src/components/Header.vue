<template>
  <div class="header_container">
        <span @click="index()">基于WebGIS的高分遥感大数据管理与维护平台</span>
  </div>
   <el-menu
    :default-active="activeIndex1"
    class="el-menu-demo1"
    mode="horizontal"
    background-color="rgba(17, 166, 180, 0.004)"
    text-color="#1e38a1"
    active-text-color="#8cdcfe"
    @select="handleSelect"
  >
    <el-menu-item index="1" @click="analyse">统计分析</el-menu-item>
    <!-- <el-menu-item index="2" @click="search">影像查询</el-menu-item> -->
  </el-menu>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo2"
    mode="horizontal"
    background-color="rgba(17, 166, 180, 0.004)"
    text-color="#1e38a1"
    active-text-color="#8cdcfe"
    @select="handleSelect"
  >
    <el-menu-item index="3" @click="manage">存储管理</el-menu-item>
    <!-- <el-menu-item index="4" @click="pubishing">服务发布</el-menu-item> -->
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
    activeIndex:'1',
  }
  },
  methods: {
    analyse(){
        this.$router.push("/newan")
      },
      search(){
        this.$router.push('/search')
      },
      manage(){
        this.$router.push('/manage')
      },
      pubishing(){
        this.$router.push('/upload')
    },
    index() {
        this.$router.push('/')
      }
  },
  mounted() {

  },
 
}
</script>

<style scoped>
.el-menu-demo2{
  position: absolute;
  width: 270px;
  right: 30px;
  top: 15.5px;
}
.header_container {
    height: 50px;
    width: 100%;
    padding: 16px 16px 0 16px;
    top: 0px;
    left: 0px;
    bottom: 0px;
    right: 0px;
    position: fixed;
    z-index: -1; 
    color: #455eff;
    font-size: 40px;
    font-family: 'Courier New', Courier, monospace;
    background-color: rgba(17, 166, 180, 0.004);
}
.el-menu-demo{
  left: 0;
  width: 450px;
  height: 60px;
}
</style>