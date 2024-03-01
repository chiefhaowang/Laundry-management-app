<template>
    <div>
        <el-tabs @tab-click="handleClick" stretch>
            <el-tab-pane label="未清洗订单">
                <el-row >            
                    <el-col :span="8" v-for="(item) in stateOneList" :key="item.id" :offset="8" :gutter="2">                
                    <div style="margin-top:15px">                    
                        <el-card @click.native="checkDrawerOne(item)" :body-style="{ padding: '0px'}" 
                        shadow="hover" class="orderCard" style = "border-radius: 12px">                     
                        <div class="card_content">                       
                            <h4>订单编号：{{item.id}}</h4>
                            <h4> {{item.year+"/"+item.month+"/"+item.day+" "+item.hour+":"+item.minute}}</h4>
                            <!-- 跑腿业务标签展示 -->
                            <div>
                            <div >
                                跑腿业务:
                                <div v-if="item.deliver_get==1" style="float:right; padding:10px">
                                    <el-tag
                                        key="送衣服务"
                                        type="success">
                                        送衣服务
                                    </el-tag>
                                    </div>
                                <div v-if="item.deliver_get!=1" style="float:right; padding:10px">
                                    <el-tag
                                        key="送衣服务"
                                        type="info">
                                        送衣服务
                                    </el-tag>
                                    </div>
                                <div v-if="item.deliver_give==1" style="float:right; padding:10px">
                                    <el-tag
                                    key="取衣服务"
                                    type="success">
                                    取衣服务
                                    </el-tag>
                                </div>
                                <div v-if="item.deliver_give!=1" style="float:right; padding:10px">
                                    <el-tag
                                    key="取衣服务"
                                    type="info">
                                    取衣服务
                                    </el-tag>
                                </div>
                            </div>
                            </div>
                        </div>
                        </el-card>                
                    </div>            
                    </el-col>        
                </el-row>
                </el-tab-pane>
            <el-tab-pane label="清洗中订单">
                <el-row >            
                    <el-col :span="8" v-for="(item) in stateTwoList" :key="item.id" :offset="8" :gutter="2">                
                    <div style="margin-top:15px">                    
                        <el-card @click.native="checkDrawerTwo(item)" :body-style="{ padding: '0px'}" 
                        shadow="hover" class="orderCard" style = "border-radius: 12px">                     
                        <div class="card_content">                       
                            <h4>订单编号：{{item.id}}</h4>
                            <h4> {{item.year+"/"+item.month+"/"+item.day+" "+item.hour+":"+item.minute}}</h4>
                            <!-- 跑腿业务标签展示 -->
                            <div>
                            <div >
                                跑腿业务:
                                <div v-if="item.deliver_get==1" style="float:right; padding:10px">
                                    <el-tag
                                        key="送衣服务"
                                        type="success">
                                        送衣服务
                                    </el-tag>
                                    </div>
                                <div v-if="item.deliver_get!=1" style="float:right; padding:10px">
                                    <el-tag
                                        key="送衣服务"
                                        type="info">
                                        送衣服务
                                    </el-tag>
                                    </div>
                                <div v-if="item.deliver_give==1" style="float:right; padding:10px">
                                    <el-tag
                                    key="取衣服务"
                                    type="success">
                                    取衣服务
                                    </el-tag>
                                </div>
                                <div v-if="item.deliver_give!=1" style="float:right; padding:10px">
                                    <el-tag
                                    key="取衣服务"
                                    type="info">
                                    取衣服务
                                    </el-tag>
                                </div>
                            </div>
                            </div>
                        </div>
                        </el-card>                
                    </div>            
                    </el-col>        
                </el-row>
            </el-tab-pane>

            <el-tab-pane label="清洗结束订单">
                <el-row >            
                    <el-col :span="8" v-for="(item) in stateThreeList" :key="item.id" :offset="8" :gutter="2">                
                    <div style="margin-top:15px">                    
                        <el-card @click.native="checkDrawerOne(item)" :body-style="{ padding: '0px'}" 
                        shadow="hover" class="orderCard" style = "border-radius: 12px">                     
                        <div class="card_content">                       
                            <h4>订单编号：{{item.id}}</h4>
                            <h4> {{item.year+"/"+item.month+"/"+item.day+" "+item.hour+":"+item.minute}}</h4>
                            <!-- 跑腿业务标签展示 -->
                            <div>
                            <div >
                                跑腿业务:
                                <div v-if="item.deliver_get==1" style="float:right; padding:10px">
                                    <el-tag
                                        key="送衣服务"
                                        type="success">
                                        送衣服务
                                    </el-tag>
                                    </div>
                                <div v-if="item.deliver_get!=1" style="float:right; padding:10px">
                                    <el-tag
                                        key="送衣服务"
                                        type="info">
                                        送衣服务
                                    </el-tag>
                                    </div>
                                <div v-if="item.deliver_give==1" style="float:right; padding:10px">
                                    <el-tag
                                    key="取衣服务"
                                    type="success">
                                    取衣服务
                                    </el-tag>
                                </div>
                                <div v-if="item.deliver_give!=1" style="float:right; padding:10px">
                                    <el-tag
                                    key="取衣服务"
                                    type="info">
                                    取衣服务
                                    </el-tag>
                                </div>
                            </div>
                            </div>
                        </div>
                        </el-card>                
                    </div>            
                    </el-col>        
                </el-row>
            </el-tab-pane>
        </el-tabs>
         
         <el-drawer
            title="详细信息"
            :visible.sync="drawerOne"
            :with-header="false">
            <el-card shadow="never">
            <div slot="header" class="clearfix">
                <span>订单详情</span>
            </div>
            <div class="personal-relation">
                <h4><div class="relation-item">用户名:
                <div style="float: right; padding-right:20px;">{{itemInDrawerOne.username}}</div>
                </div></h4>
            </div>
            <div class="personal-relation">
                <h4><div class="relation-item">用户手机号:
                <div style="float: right; padding-right:20px;">{{itemInDrawerOne.phone}}</div>
                </div></h4>
            </div>
            <div class="personal-relation">
                <h4><div class="relation-item">订单内容：
                <div style="float: right; padding-right:20px;">{{itemInDrawerOne.content}}</div>
                </div></h4>
            </div>
            
            <el-divider></el-divider>
            <span>跑腿业务</span>
            <div class="personal-relation">
                <h4><div class="relation-item">地址:
                <div style="float: right; padding-right:20px;">{{itemInDrawerOne.address}}</div>
                </div></h4>
            </div>
            </el-card>
            <div style="float:right; padding-right:20px;padding:10px">
                <el-button @click="orderIncharge">申领订单</el-button>
            </div>
        </el-drawer>

        <el-drawer
            title="详细信息"
            :visible.sync="drawerTwo"
            :with-header="false">
            <el-card shadow="never">
            <div slot="header" class="clearfix">
                <span>订单详情</span>
            </div>
            <div class="personal-relation">
                <h4><div class="relation-item">用户名:
                <div style="float: right; padding-right:20px;">{{itemInDrawerOne.username}}</div>
                </div></h4>
            </div>
            <div class="personal-relation">
                <h4><div class="relation-item">用户手机号:
                <div style="float: right; padding-right:20px;">{{itemInDrawerOne.phone}}</div>
                </div></h4>
            </div>
            <div class="personal-relation">
                <h4><div class="relation-item">订单内容：
                <div style="float: right; padding-right:20px;">{{itemInDrawerOne.content}}</div>
                </div></h4>
            </div>
            </el-card>
            <div style="float:right; padding-right:20px;padding:10px">
                <el-button @click="orderIncharge">申领订单</el-button>
            </div>
        </el-drawer>
    </div>
</template>

<script>
export default {
    created(){
        this.getInfo();
    },
    data(){
        return{

        //个状态下订单列表
        stateOneList:[],
        stateTwoList:[],
        stateThreeList:[],

        //详细信息抽屉显示，one：第一第三状态，two：第二状态
        drawerOne:false,
        drawerTwo:false,

        itemInDrawerOne:[],

        //订单申领
        orderInchargeForm:{
          id:"",
          clerkname:"",
          clerkphone:"",
          state:""
        }
        }
    },

    methods:{
      handleClick(tab, event) {
            //console.log(tab, event);
      },

      async getStateOneList(){
          const {data:res}=await this.$http.get("/freeoneorder");
          this.stateOneList=res;
      },

      async getStateTwoList(){
          const {data:res}=await this.$http.get("/freetwoorder");
          this.stateTwoList=res;
      },

      async getStateThreeList(){
          const {data:res}=await this.$http.get("/freethreeorder");
          this.stateThreeList=res;
      },

      getInfo(){
        this.userIfo=JSON.parse(window.sessionStorage.getItem("user"));
        this.orderInchargeForm.clerkname=this.userIfo.username;
        this.orderInchargeForm.clerkphone=this.userIfo.id;
        this.getStateOneList();
        this.getStateTwoList();
        this.getStateThreeList();
      },

      checkDrawerOne(item){
        this.drawerOne=true;
        this.itemInDrawerOne=item;
      },

      checkDrawerTwo(item){
        this.drawerTwo=true;
        this.itemInDrawerOne=item;
      },

      async orderIncharge(){
            const confirmResult = await this.$confirm('是否申领订单？','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.info("取消申领");
            }
            this.orderInchargeForm.id=this.itemInDrawerOne.id;
            this.orderInchargeForm.state=this.itemInDrawerOne.state;
            console.log("order in charge "+this.orderInchargeForm.id);
            const {data:res}=await this.$http.put("/orderincharge",this.orderInchargeForm);
            if(res=="error") return this.$message.error("订单申领失败");
                  this.$message.success("订单申领成功");
            location.reload()

      }

    }
}
</script>

<style lang="less" scoped>
::v-deep .el-tabs__nav-scroll{
  width: 50%!important;
  margin: 0 auto!important;
}

.orderCard{
  background-color: #E4E7ED;
}

.card_content{
  padding-left: 15px;
}

.personal-relation {
  font-size: 16px;
  padding: 0px 1px 1px;
  margin-right: 1px;
  width: 100%
}
</style>