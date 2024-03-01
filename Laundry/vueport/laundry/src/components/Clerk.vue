<template>
<!-- container布局引入 -->
<el-container class="clerk_container">
    <!-- 头部 -->
  <el-header>
      <div>
        <img src="../assets/Logo.png" alt />
        <span>WonderLaundry</span>
      </div>
      <div>Clerk</div>
        </el-header>


    <!-- 主体部分 -->
  <el-container>
    <!-- 侧边栏 -->
    <el-aside width="200px">
    <el-menu background-color="#545c64" text-color="#fff" active-text-color="#ffd04b"
    :router="true">
        <!-- 一级菜单 -->
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>日常业务</span>
        </template>
        <!-- 二级菜单 -->
        <el-menu-item index="clerkselforder">
            <template slot="title">
                <i class="el-icon-location"></i>
                <span>我的订单</span>
            </template>
        </el-menu-item>
        <el-menu-item index="clerkorder">
            <template slot="title">
                <i class="el-icon-location"></i>
                <span>闲置订单列表</span>
            </template>
        </el-menu-item>
    <!-- 一级菜单 -->
      </el-submenu>
     <el-menu-item index="clerkhisorder">
        <i class="el-icon-coin"></i>
        <span slot="title">历史订单查询</span>
      </el-menu-item>

      <el-menu-item index="clerkactivity">
        <i class="el-icon-guide"></i>
        <span slot="title">地区检索</span>
      </el-menu-item>

      <el-menu-item index="/clerkselfifo">
        <i class="el-icon-setting"></i>
        <span slot="title">个人中心</span>
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