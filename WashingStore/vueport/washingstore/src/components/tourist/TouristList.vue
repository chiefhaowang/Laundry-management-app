<template>
<div>
  <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{path:'/client'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>洗衣订单下单</el-breadcrumb-item>
        </el-breadcrumb>
  <el-container>
    <el-aside width="70%">
      <el-table
        :data="clothePrice"
        stripe
        style="width: 100%">
        <el-table-column
          prop="name"
          label="名称"
          >
        </el-table-column>
        <el-table-column
          prop="shorter"
          label="小号"
          >
        </el-table-column>
        <el-table-column
          prop="mid"
          label="中号"
          >
        </el-table-column>
        <el-table-column
          prop="longer"
          label="长号"
          >
        </el-table-column>
        <el-table-column
          prop="suplong"
          label="加长"
          >
        </el-table-column>
        <el-table-column label="操作">
            <!-- slot-scope="scope" -->
          <template slot-scope="scope">
              <el-tooltip effect="dark" content="调整数量" placement="top-start" :enterable="false">
              <el-button @click="showOrderDialog(scope.row)" type="primary" icon="el-icon-edit" size="mini" ></el-button>
              </el-tooltip>
          </template>
       </el-table-column>

      </el-table>

      <!-- 调整订单数量 -->
       <!-- @close="dialogClosed" -->
        <el-dialog :title="items.name" :visible.sync="dialogVisible" width="50%">
            
          <template>
            <div>
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="size" label="尺码"></el-table-column>
                <el-table-column prop="price" label="尺码价格">
                  <template slot-scope="scope" >
                    <el-input v-model="scope.row.price"></el-input>
                  </template>
                </el-table-column>
                <el-table-column sortable label="订单数量" prop="number">
                  <template slot-scope="scope">
                    <el-input-number v-model="scope.row.number" :disabled="scope.row.isAble"></el-input-number>
                  </template>
                </el-table-column>          
                <el-table-column sortable label="总金额">
                  <template slot-scope="scope">
                  {{ (scope.row.price * scope.row.number).toFixed(2) }}
                  </template>
                </el-table-column>
               
              </el-table>

              <div style="line-height:30px;">
                商品总价：<span>{{ calTotalPrice }}</span>
              </div>
            </div>
          </template>

            <!-- 底部按钮 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="addOnceOrder">确 定</el-button>
            </span>
        </el-dialog>

    </el-aside>
    <el-main>
      <!-- 结算卡片 -->
      <div class="grid-content bg-purple">
        <el-card class="box-card">
            <div slot="header">
                <span class="card-head">结算</span>
            </div>
            
            <div class="personal-relation">
                <div>{{orderMenu}}</div>
            </div>
          <el-divider></el-divider>
            <div style="float:right; padding-right:20px;padding:10px">
                <span class="sender">
                  总价: {{totalPrice}}
                  </span>  
            </div>
            <div align="middle" style="padding-top:80px">
                <el-button  tpye="primary" plain @click="addOrder">提交订单</el-button>
            </div>
        </el-card>
        
    </div>
    </el-main>
  </el-container>
  <!-- 新增订单确认弹窗 -->
        <el-dialog title="洗衣下单" :visible.sync="addDialogVisible" width="50%">
            <el-container>
              <el-aside width="60%">
                <el-card>
                   <div slot="header">
                      <span class="card-head">确定订单</span>
                  </div>
                  <div class="personal-relation">
                      <div>{{orderMenu}}</div>
                  </div>
                  <el-divider></el-divider>
                  <div style="float:right; padding-right:20px;padding:10px">
                      <span class="sender">
                        总价: {{totalPrice}}
                        </span>  
                  </div>
                </el-card>
              </el-aside>
              <el-main>
                <h3>是否需要上门取/送衣业务？</h3>
                <div>
                  <el-checkbox v-model="checkGive">上门取衣</el-checkbox>
                  <el-checkbox v-model="checkGet">上门送衣</el-checkbox> 
                </div>
              </el-main>
            </el-container>
            <!-- 底部按钮 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="addOrderDia">确 定</el-button>
            </span>
        </el-dialog>
</div>
</template>

<script>
  export default {
    created(){
      this.getPriceList();
      this.getInfo();
    },

    data() {
      return {

        items:[],
        dialogVisible:false,
        totalPrice:0,
        temPrice:0,
        orderMenu:[],
        tableData:[{size:"小号",price:0,number:0,isAble:false},{size:"中号",price:0,number:0,isAble:false},{size:"长号",price:0,number:0,isAble:false},{size:"加长号",price:0,number:0,isAble:false}],

        clothePrice:[],

        //下单弹窗
        addDialogVisible:false,
        addForm:{
          id:"",
          username:"",
          phone:"",
          orderpay:"",
          content:"test2",
          state:1,
          deliver_give:"",
          deliver_get:"",
          year:"",
          month:"",
          week:"",
          day:"",
          hour:"",
          minute:"",
          second:"",
        },
        //多选框
        checkGive:false,
        checkGet:false,
        
      }
    },

    computed:{
    // 商品总价
    calTotalPrice(){
      const total = this.tableData.map((row) => row.number * row.price).reduce((total, num) => total + num);
      this.temPrice=total;
      console.log(this.temPrice);
      return total;
      }
    },

    methods:{
    async getPriceList(){
			const {data:res_clo}=await this.$http.get("/clientclotheprice");
			this.clothePrice=res_clo;	
			console.log(this.clothePrice[1].name);
		},

    showOrderDialog(item){
      console.log(item);
      this.dialogVisible=true;
      this.items=item;
      for(var i=0;i<4;i++){
        this.tableData[i].isAble=false;
      }
      this.tableData[0].price=item.shorter;
      this.tableData[1].price=item.mid;
      this.tableData[2].price=item.longer;
      this.tableData[3].price=item.suplong;
      for(var i=0;i<4;i++){
        this.tableData[i].number=0;
        if(this.tableData[i].price==0){
          this.tableData[i].isAble=true;
        }
      }
    },

  
    addOnceOrder(){
      this.totalPrice+=this.temPrice;
      console.log(this.totalPrice);
      var str="/"+this.items.name+": ";
      console.log(str);
      for(var i=0;i<4;i++){
        if(this.tableData[i].number!=0){
          console.log(this.tableData[i].size);
          str+=`${this.tableData[i].size}*${this.tableData[i].number} `;
        }
      }
      console.log(str);
      this.orderMenu.push(str);
      this.dialogVisible=false;
      
    },

    addOrder(){
      this.addDialogVisible=true;
    },

    async addOrderDia(){
      console.log("addOrderDia");
      //数据结构填充
      this.addForm.orderpay=this.totalPrice;
      //this.addForm.content=this.orderMenu;
      if(this.checkGive==true){
        this.addForm.deliver_give=1;
      }
      if(this.checkGet==true){
        this.addForm.deliver_get=1;
      }
      let date = new Date();
      this.addForm.year=date.getFullYear();
      this.addForm.month=date.getMonth();
      this.addForm.day=date.getDate();
      this.addForm.week=date.getDay();
      this.addForm.hour=date.getHours();
      this.addForm.minute=date.getMinutes();
      this.addForm.second=date.getSeconds();

      //订单号生成
      this.addForm.id=`#${this.addForm.phone}${this.addForm.day}${this.addForm.hour}${this.addForm.minute}${this.addForm.second}`;
      //数据流
      // const {data:res}=await this.$http.post("addorder",this.addForm);
      // if(res!="success"){
      //   return this.$message.error("支付失败");
      // }
      this.$message.success("模拟操纵成功");
      this.addDialogVisible=false;
      location.reload();
    },

    getInfo(){
      let us=JSON.parse(window.sessionStorage.getItem("user"));
      this.addForm.username=us.username;
      this.addForm.phone=us.id;
    },

    // addDialogClosed(){
    //     this.$refs.addFormRef.resetFields();
    //   },
    }
  }
</script>

<style lang="less" scoped>
// .dialog-footer{

// }
.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}
.card-head{
   font-size: 15px;
}
  .box-card {
    width: 480px;
  }

</style>