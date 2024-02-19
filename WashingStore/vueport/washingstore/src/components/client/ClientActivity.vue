<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{path:'/client'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>店铺活动</el-breadcrumb-item>
        </el-breadcrumb>
      
      <el-button @click="aliPay">支付宝支付</el-button>
      
  </div>
</template>

<script>
export default {
  data(){
    return{
      payForm:{
        id:"#111111",
        price:"133",
        title:"WonderLaundry"
      },

      rawHtml:"",
    }
  },

  methods:{
    async aliPay(){
      const confirmResult = await this.$confirm('将跳转到支付页面','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.info("取消支付");
            }
      const {data:res} = await this.$http.post("/alipay",this.payForm);
      const div=document.createElement('divform');
      div.innerHTML=res;
      document.body.appendChild(div);
      document.forms[0].submit();
      console.log("enter pay page!");

    }
  }
}
</script>

<style lang="less" scoped>
.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}
</style>