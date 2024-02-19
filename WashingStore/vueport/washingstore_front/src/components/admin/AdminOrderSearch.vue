<template>
    <div>
        <!-- 导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{path:'/admin'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>营业详情</el-breadcrumb-item>
            <el-breadcrumb-item>订单管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row :gutter="25">
                <el-col :span="10">     
                    <el-input placeholder="搜索用户名" v-model="queryInfo.query" clearable @clear="getOrderList">
                    <el-button slot="append" icon="el-icon-search" @click="getOrderList"></el-button>
                    </el-input>
                </el-col>
            </el-row>   
            <!-- 历史订单展示 -->
           <el-table :data="OrderList" style="width: 100%">
                <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="用户名">
                        <span>{{ props.row.username }}</span>
                    </el-form-item>
                    <el-form-item label="创建时间">
                        <span>{{ props.row.year+"/"+props.row.month+"/"+props.row.day+" "+props.row.hour+":"+props.row.minute }}</span>
                    </el-form-item>
                    <el-form-item label="用户手机号">
                        <span>{{ props.row.phone }}</span>
                    </el-form-item>
                    <el-form-item label="订单价格">
                        <span>{{ props.row.orderpay }}</span>
                    </el-form-item>
                    <el-form-item label="订单内容">
                        <span>{{ props.row.content }}</span>
                    </el-form-item>
                    <el-form-item label="第一阶段负责人">
                        <span>{{ props.row.first_clerkname }}</span>
                    </el-form-item>
                    <el-form-item label="第二阶段负责人">
                        <span>{{ props.row.second_clerkname }}</span>
                    </el-form-item>
                    <el-form-item label="第三阶段负责人">
                        <span>{{ props.row.third_clerkname }}</span>
                    </el-form-item>
                    </el-form>
                </template>
                </el-table-column>
                <el-table-column label="订单编号" prop="id"></el-table-column>
                <el-table-column label="订单创建时间" prop="year,month,day,hour,minute">
                    <template slot-scope="scope">
                    {{ scope.row.year+"/"+scope.row.month+"/"+scope.row.day+" "+scope.row.hour+":"+scope.row.minute }}
                  </template>
                </el-table-column>
                <el-table-column label="订单内容" prop="content"></el-table-column>
               
            </el-table>
            <!-- 分页组件 -->
            <div>
                <el-pagination @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="queryInfo.number"
                    :page-sizes="[1, 2, 5, 10]"
                    :page-size="queryInfo.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
            </div>
        </el-card>

    </div>
</template>

<script>
export default {
    created(){
        this,this.getOrderList();
    },

    data(){
        return {
            queryInfo:{
                query:"",//查询信息
                pageNum:1,//当前页面
                pageSize:10,//每页最大数
            },
            OrderList:[],
            total:0//订单总量
        }
    },

    methods:{
        async getOrderList(){
            const {data:res} = await this.$http.get("/clerkhisorder",{params:this.queryInfo});
            this.OrderList=res.data;
            this.total=res.number;
            console.log(res.data);
        },
        //分页工具
        handleSizeChange(newSize){
            this.queryInfo.pageSize=newSize;
            this.getClientList();
        },
        //分页
        handleCurrentChange(newPage){
            this.queryInfo.pageNum=newPage;
            this.getClientList();
        },
    }
}
</script>

<style lang="less" scoped>
.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}
.demo-table-expand {
font-size: 0;
}
.demo-table-expand label {
width: 90px;
color: #99a9bf;
}
.demo-table-expand .el-form-item {
margin-right: 0;
margin-bottom: 0;
width: 50%;
}
</style>