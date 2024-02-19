<template>
    <div>
        <el-container>
            <el-aside width="70%">
				<!-- 导航 -->
        		<el-breadcrumb separator-class="el-icon-arrow-right">
            		<el-breadcrumb-item :to="{path:'/client'}">首页</el-breadcrumb-item>
            		<el-breadcrumb-item>自助洗衣</el-breadcrumb-item>
            		<el-breadcrumb-item>订单查询</el-breadcrumb-item>
        		</el-breadcrumb>
				<div>

				</div>
				
				<el-row >            
					<el-col :span="15" v-for="(item) in orderList" :key="item.id" :offset="7" :gutter="2">                
						<div style="margin-top:15px">                    
							<el-card @click.native="checkDrawer(item)" :body-style="{ padding: '0px'}" shadow="hover" 
							class="orderCard" style = "border-radius: 12px">  
								<div>
									<el-steps :active="item.state" simple>
										<el-step title="步骤 1"></el-step>
										<el-step title="步骤 2"></el-step>
										<el-step title="步骤 3"></el-step>
									</el-steps>
								</div>
								<div class="card_word">
									<div ><h4>订单编号：{{item.id}}</h4></div>
									<div >订单内容：{{item.content}}</div> 
									<p></p>        
									<div>{{item.year+"/"+item.month+"/"+item.day+" "+item.hour+":"+item.minute}}</div>
									<div style="float: right; padding-right:20px;">订单总价：{{item.orderpay}}</div>
								</div>
							<!-- <el-button style="primary">订单详情</el-button> -->
							</el-card>                
						</div>            
					</el-col>        
				</el-row>

				<!-- 订单卡片详细信息 -->
				<el-drawer
					title="详细信息"
					:visible.sync="drawer"
					:with-header="false">
					<el-card shadow="never">
						<div slot="header" class="clearfix">
							<span>订单详情</span>
						</div>
						<div class="personal-relation">
							  <h4><div class="relation-item">阶段一负责人:
								<div style="float: right; padding-right:20px;">{{itemInDrawer.first_clerkphone}}</div>
								<div style="float: right; padding-right:20px;">{{itemInDrawer.first_clerkname}}</div>
								</div></h4>
						</div>
						<div class="personal-relation">
							  <h4><div class="relation-item">阶段二负责人:
								<div style="float: right; padding-right:20px;">{{itemInDrawer.second_clerkphone}}</div>
								<div style="float: right; padding-right:20px;">{{itemInDrawer.second_clerkname}}</div>
								</div></h4>
						</div>
						<div class="personal-relation">
							  <h4><div class="relation-item">阶段三负责人:
								<div style="float: right; padding-right:20px;">{{itemInDrawer.third_clerkphone}}</div>
								<div style="float: right; padding-right:20px;">{{itemInDrawer.third_clerkname}}</div>
								</div></h4>
						</div>
						
						<el-divider></el-divider>
						<span>跑腿业务选择:</span>
							<div class="personal-relation">
							  <h4><div>代取业务:
								<el-switch
									v-model="clo_give"
									active-color="#13ce66"
									inactive-color="#909399"
									style="float:right; padding-right:20px"
									:disabled="itemInDrawer.state==1?false:true"
									@change="handleSwitch()"
									>
								</el-switch>
								</div></h4>
							</div>
							<div class="personal-relation">
							  <h4><div>代送业务:
								<el-switch
									v-model="clo_get"
									active-color="#13ce66"
									inactive-color="#909399"
									style="float:right; padding-right:20px"
									@change="handleSwitch()"
									>
								</el-switch>
								</div></h4>
							</div>
					</el-card>
					<div style="float:right; padding-right:20px;padding:10px">
						<el-button :disabled="itemInDrawer.state==3?false:true" @click="finishOrder">结束订单</el-button>
					</div>
				</el-drawer>
            </el-aside>
			<el-main>
            <el-card>  
				<!-- 历史订单展示 -->
				<el-table :data="hisOrderList" style="width: 100%">
						<el-table-column type="expand">
						<template slot-scope="props">
							<el-form label-position="left" inline class="demo-table-expand">
							<el-form-item label="订单编号：">
								<span>{{ props.row.id }}</span>
							</el-form-item>
							<el-form-item label="订单价格：">
								<span>{{ props.row.orderpay }}</span>
							</el-form-item>
							<el-form-item label="订单内容：">
								<span>{{ props.row.content }}</span>
							</el-form-item>
							</el-form>
						</template>
						</el-table-column>
						<el-table-column label="创建时间" prop="year,month,day,hour,minute">
							<template slot-scope="scope">
								{{ scope.row.year+"/"+scope.row.month+"/"+scope.row.day+" "+scope.row.hour+":"+scope.row.minute }}
							</template>
						</el-table-column>
						<el-table-column label="商品总价" prop="orderpay"></el-table-column>
					</el-table>
			</el-card>
			</el-main>
        </el-container>
    </div>
</template>

<script>
export default {
	created(){
		this.getOrderList();	
		this.getHisOrderList();
	},

    data(){
		return{
			orderList:[],
			hisOrderList:[],

			clo_give:false,
			clo_get:false,

			clothePrice:[],
			stuffPrice:[],

			//抽屉显示详细信息
			drawer:false,
			itemInDrawer:[]


		}
	},

	methods:{
		async getOrderList(){
			let us=JSON.parse(window.sessionStorage.getItem("user"));
			const {data:res} = await this.$http.get("orderlist?phone="+us.id);
			console.log("orderlist");
			console.log(res);
			this.orderList=res;
		},

		async getHisOrderList(){
			let us=JSON.parse(window.sessionStorage.getItem("user"));
			const {data:res}=await this.$http.get("orderhislist?phone="+us.id);
			console.log(res);
			this.hisOrderList=res;
		},

		checkDrawer(item){
			console.log("click card: "+item.day);
			this.drawer=true;
			this.itemInDrawer=item;
			console.log(item.deliver_give);
			console.log(item.deliver_get);
			if(item.deliver_give==1)
				this.clo_give=true;
			else
				this.clo_give=false;
				
			if(item.deliver_get==1)
				this.clo_get=true;
			else
				this.clo_get=false;
		},

		addDialogClosed(){
            this.$refs.addFormRef.resetFields();
        },

		async needGive(){
			console.log("need give");

		},

		async needGet(){
			console.log("need get");
		},

		finishOrder(){
			console.log("finish order!");
		},
		async handleSwitch(){
			const confirmResult = await this.$confirm('是否需要预约该业务?','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.error("已取消操作");
            }else{
				return this.$message.success("预约成功");
			}
		}
	}
}
</script>

<style lang="less" scoped>
.el-container{
    height: 100%;
}
// .el-aside{
    
    
// }
// .el-main{

// }
.personal-relation {
  font-size: 16px;
  padding: 0px 5px 5px;
  margin-right: 1px;
  width: 100%
}
.steps{
  padding-top:10px;
}

.orderCard{
  background-color: #E4E7ED;
}

.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}
.el-steps{
	background-color: #E4E7ED;
	height: 100%;
}
.card_word{
	font-size: 16px;
  padding: 0px 5px 5px;
  margin-right: 1px;
  width: 100%
}
</style>