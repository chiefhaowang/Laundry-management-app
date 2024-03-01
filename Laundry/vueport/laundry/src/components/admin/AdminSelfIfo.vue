<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{path:'/client'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>个人中心</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button style="float: right; padding-right:20px;" type="danger" icon="el-icon-circle-close" @click="logout"></el-button>
        <el-row :gutter="20" style="margin-top:10px;" align="middle"  type="flex">
            <el-col :span="8" :offset="8">
                <div class="grid-content bg-purple">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>个人中心</span>
                        </div>
                        <div class="name-role">
                            <span class="sender">
                              <h2>管理员 - {{dataForm.username}}</h2>
                              </span>  
                        </div>
                        
                <el-divider></el-divider>
                        <div class="personal-relation">
                            <div class="relation-item">用户名：  <div style="float: right; padding-right:20px;">{{dataForm.username}}</div></div>
                        </div>
                        <div class="personal-relation">
                            <div class="relation-item">手机号：  <div style="float: right; padding-right:20px;">{{dataForm.id}}</div></div>      
                        </div>
                        <div class="personal-relation">
                            <div class="relation-item">密码：  <div style="float: right; padding-right:20px;">{{dataForm.password}}</div></div>      
                        </div>
                        <div class="personal-relation">
                            <div class="relation-item">邮箱：  <div style="float: right; padding-right:20px;">{{dataForm.email}}</div></div>      
                        </div>
                        <div align="middle">
                          <el-button size="mini" @click="updateIfo">修改个人信息</el-button>
                        </div>
                    </el-card>
                </div>
            </el-col>
      </el-row>

      <el-dialog title="修改个人信息" :visible.sync="dialogVisible" width="40%" >
            <el-form :model="dataForm" ref="dataFormRef" label-width="100px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="dataForm.username"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="id">
                    <el-input v-model="dataForm.id"  disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="dataForm.password"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="dataForm.email"></el-input>
                </el-form-item>
                
            </el-form>
            
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="editUserIfo">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>.

<script>
export default {
created(){
  this.getUserIfo();

},
data(){
    return{
      dataForm:{
        username: "",
        id: "",
        password: "",
        email:"",
        address:""
      },

      dialogVisible:false
    }
  },
methods:{

    getUserIfo(){
      this.dataForm=JSON.parse(window.sessionStorage.getItem("user"));
    },

    updateIfo(){
      this.dialogVisible=true;
    },

    editUserIfo(){
      this.$refs.dataFormRef.validate(async valid =>{
              if(!valid) return;
              const {data:res} = await this.$http.put("editclient",this.dataForm);
              if(res=="error") return this.$message.error("修改失败");
              this.$message.success("修改成功");
              //隐藏
              this.dialogVisible=false;
              location.reload();
          })  
    },
    logout(){
            window.sessionStorage.clear();//清楚session
            this.$router.push("/login");
            console.log("go back");
        },
  },
  
}
</script>

<style lang="less" scoped>


//卡片样式
 .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    
  }
//文本样式区
  .name-role {
    font-size: 16px;
    padding: 5px;
   text-align:center;
  }
   .sender{
      text-align:center;
    }
.registe-info{
  text-align: center;
  padding-top:10px;
}
.personal-relation {
  font-size: 16px;
  padding: 0px 5px 15px;
  margin-right: 1px;
    width: 100%
}

.relation-item {
  padding: 12px;
   
}
.dialog-footer{
  padding-top:10px ;
  padding-left: 10%;
}
//布局样式区
   .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}
</style>
