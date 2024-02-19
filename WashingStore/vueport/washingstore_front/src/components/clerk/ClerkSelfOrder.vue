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
                <el-card @click.native="checkDrawerThree(item)" :body-style="{ padding: '0px'}" 
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

  <!-- 抽屉详细信息显示 -->
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
						<el-button @click="finishState">结束状态</el-button>
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
          <h4><div class="relation-item">订单内容:
          <div style="float: right; padding-right:20px;">{{itemInDrawerOne.content}}</div>
          </div></h4>
      </div>
    </el-card>
    <div style="float:right; padding-right:20px;padding:10px">
						<el-button @click="finishState">结束状态</el-button>
					</div>
  </el-drawer>

  <el-drawer
    title="详细信息"
    :visible.sync="drawerThree"
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
						<el-button @click="finishOrder">结束订单</el-button>
					</div>
  </el-drawer>
  </div>
</template>


<script>
  export default {
    created(){
        this.getClerkIfo();
    },

    data() {
      return {
        userIfo:[],
        // 状态信息
        stateOne:{
            state:1,
            first_clerkname:"",
            first_clerkphone:"",
        },
        stateTwo:{
            state:2,
            second_clerkname:"",
            second_clerkphone:"",
        },
        stateThree:{
            state:3,
            third_clerkname:"",
            third_clerkphone:"",
        },
        //个状态下订单列表
        stateOneList:[],
        stateTwoList:[],
        stateThreeList:[],

        drawerOne:false,
        drawerTwo:false,
        drawerThree:false,

        itemInDrawerOne:[],

        //修改订单状态
        editState:{
          id:"",
          state:""
        },
        continueInCharge:{
          id:"",
          clerkname:"",
          clerkphone:"",
          state:""
        },
        //结束订单状态
        endOrder:{
          id:""
        }
      };
    },

    methods: {
      async getStateOneList(){
        const {data:res} = await this.$http.get("/stateonelist",{params:this.stateOne});
        this.stateOneList=res;
        console.log(res);
      },

      async getStateTwoList(){
        const {data:res} = await this.$http.get("/statetwolist",{params:this.stateTwo});
        this.stateTwoList=res;
      },

      async getStateThreeList(){
        const {data:res} = await this.$http.get("/statethreelist",{params:this.stateThree});
        this.stateThreeList=res;
      },

      getClerkIfo(){
        this.userIfo=JSON.parse(window.sessionStorage.getItem("user"));
        this.stateOne.first_clerkname=this.userIfo.username;
        this.stateOne.first_clerkphone=this.userIfo.id;
        this.stateTwo.second_clerkname=this.userIfo.username;
        this.stateTwo.second_clerkphone=this.userIfo.id;
        this.stateThree.third_clerkname=this.userIfo.username;
        this.stateThree.third_clerkphone=this.userIfo.id;
        this.continueInCharge.clerkname=this.userIfo.username;
        this.continueInCharge.clerkphone=this.userIfo.id;
        this.getStateOneList();
        this.getStateTwoList();
        this.getStateThreeList();     
      },
      
      handleClick(tab, event) {
            
      },

      checkDrawerOne(item){
        this.drawerOne=true;
        this.itemInDrawerOne=item;
      },

      checkDrawerTwo(item){
        this.drawerTwo=true;
        this.itemInDrawerOne=item;
      },

      checkDrawerThree(item){
        this.drawerThree=true;
        this.itemInDrawerOne=item;
      },

      async finishState(){
        const confirmResult = await this.$confirm('该阶段订单是否处理完成？','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.info("已取消状态变更");
            }
            this.editState.id=this.itemInDrawerOne.id;
            this.editState.state=this.itemInDrawerOne.state + 1;
            console.log("edit state"+this.editState.state);
            const {data:res}=await this.$http.put("/editorderstate",this.editState);
            if(res=="error") return this.$message.error("状态变更失败");
                  this.$message.success("状态变更");
            
        const confirmResult2 = await this.$confirm('是否负责处理订单下一阶段','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult2!='confirm'){//取消操作
                return this.$message.info("放弃订单业务");
            }
            console.log("222");
            this.continueInCharge.id=this.editState.id;
            this.continueInCharge.state=this.editState.state;
            const {data:res2}=await this.$http.put("/orderincharge",this.continueInCharge);
            if(res2=="error") return this.$message.error("订单申领失败");
                  this.$message.success("订单申领成功");
            location.reload()
      },

      async finishOrder(){
        const confirmResult = await this.$confirm('用户是否同意结束订单？','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.info("订单状态保留");
            }
            console.log("333");
            this.endOrder.id=this.itemInDrawerOne.id;
            const {data:res}=await this.$http.put("/endorder",this.endOrder);
            if(res=="error") return this.$message.error("订单保留");
                  this.$message.success("订单结束");
            location.reload()
      }
    }
  };
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

// .el-tag{
//   float:right;
//   padding-right: 10px;
// }

.personal-relation {
  font-size: 16px;
  padding: 0px 1px 1px;
  margin-right: 1px;
  width: 100%
}
</style>