<template>
<!-- container布局引入 -->
<el-container class="clerk_container">
    <!-- 头部 -->
  <el-header>
      <div>
        <img src="../assets/Logo.png" alt />
        <span>Tourist</span>
      </div>
      <el-button type="info" @click="logout">回到首页</el-button>
        </el-header>


    <!-- 主体部分 -->
  <el-container>
    <!-- 侧边栏 -->
    <el-aside width="200px">
    <el-menu background-color="#545c64" text-color="#fff" active-text-color="#ffd04b"
    :router="true">

     <el-menu-item index="touristlist">
        <i class="el-icon-menu"></i>
        <span slot="title">衣物清洗价格单</span>
      </el-menu-item>

      <el-menu-item index="touriststufflist">
        <i class="el-icon-document"></i>
        <span slot="title">物品清洗价格单</span>
      </el-menu-item>


    </el-menu>
        </el-aside>

    <!-- 中央内容 -->
    <el-main>
        <router-view></router-view>
    </el-main>

  </el-container>
</el-container>
</template>

<script>
export default {
//页面加载即刻启动
    created(){
        //查询list
        this.getList();
    },

    methods:{
        logout(){
            window.sessionStorage.clear();//清楚session
            this.$router.push("/login");
            console.log("go back");
        },
        //加载信息列表
        getList(){

            console.log("Load list!");
        }
    }
    
}
</script>

<style lang='less' scoped>
.clerk_container{
    position:absolute;
    height: 100%;
    width: 100%;

}

.el-header{
    background-color: #283d4b;
    display: flex;
    justify-content: space-between;//左右贴边
    padding-left: 0%;
    color:#fff;
    font-size: 20px;
    align-items: center;
    >div{
    display:flex;
    align-items: center;
        span{
            margin-left: 15px;
        }
    }
    
}
.el-aside{
    background-color: #333744;
    .el-menu{
        border-right: none;
    }
   
}
.el-main{
    background-color: #eaedf1;
}
img{
    width:55px;
    height: 55px;
}
</style>