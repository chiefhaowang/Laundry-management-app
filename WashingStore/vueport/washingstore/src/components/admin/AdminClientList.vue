<template>
    <div>
        <!-- 导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{path:'/admin'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户信息</el-breadcrumb-item>
            <el-breadcrumb-item>客户信息</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 用户列表主体 -->
        <el-card>
            <el-row :gutter="25">
                <el-col :span="10">
                    <!-- 搜索区域 -->
                    <el-input placeholder="搜索内容" v-model="queryInfo.query" clearable @clear="getClientList">

                    <el-button slot="append" icon="el-icon-search" @click="getClientList"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible=true">添加用户</el-button>
                </el-col>
            </el-row>
            <!-- 客户列表展示 -->
            <el-table :data="clientList" border stripe>
                <!-- 索引 -->
                <el-table-column type="index"></el-table-column>
                <el-table-column label="用户名" prop="username"></el-table-column>
                <el-table-column label="手机号" prop="id" :formatter="showPhone"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="地址" prop="address"></el-table-column>
                <el-table-column label="操作">
                     <!-- slot-scope="scope" -->
                    <template slot-scope="scope">
                        <!-- 修改 -->
                        <el-tooltip effect="dark" content="修改用户信息" placement="top-start" :enterable="false">
                        <el-button @click="showEditDialog(scope.row.id)" type="primary" icon="el-icon-edit" size="mini" ></el-button>
                        </el-tooltip>
                        <!-- 删除 -->
                        <el-tooltip effect="dark" content="删除用户" placement="top-start" :enterable="false">
                        <el-button @click="deleteClient(scope.row.id)" type="danger" icon="el-icon-delete" size="mini"></el-button>
                        </el-tooltip>
                    </template>
                </el-table-column>
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

        <!-- 新增用户区域 -->
        <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px"> 
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="addForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="addForm.password"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="id">
                    <el-input v-model="addForm.id"></el-input>
                </el-form-item>
                <el-form-item label="验证码" >
                        <el-input prop="code" v-model="addForm.emailnew" placeholder="4位验证码"></el-input>
                            <button @click="getTestCode()" :disabled="!show">
                            <span>发送验证码</span>
                            </button>
                    </el-form-item>
            </el-form>
            <!-- 底部按钮 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="addClient">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 修改对话框 -->
        <el-dialog title="修改用户信息" :visible.sync="editFormVisible" width="50%" @close="editDialogClosed">
            <el-form :model="editForm" :rules="updateFormRules" ref="editFormRef" label-width="70px"> 
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="editForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="editForm.password" show-password></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="id">
                    <el-input v-model="editForm.id" disabled show-password></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="editForm.email"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="editForm.address"></el-input>
                </el-form-item>
            </el-form>
            <!-- 底部按钮 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="editFormVisible=false">取 消</el-button>
                <el-button type="primary" @click="editClient">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    created(){
        this.getClientList();
    },
    
    data(){
        return{
            //查询信息实体
            queryInfo:{
                query:"",//查询信息
                pageNum:1,//当前页面
                pageSize:10,//每页最大数
            },
            clientList:[],//用户列表
            total:0,//总记录数量

            addDialogVisible:false,//对话框状态
            addForm:{
                username:'',
                password:'',
                id:'',
                emailnew:""//存验证码
            },
            sendPhone:{
                phone:""
            },
             //验证码
            sendedCode:"",
            count:"",
            show:true,

            //添加表单验证
            addFormRules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 1, max: 8, message: '长度在1到8个字符', trigger: 'blur' }
                ],
                password:[
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 1, max: 30, message: '长度在1到30个字符', trigger: 'blur' }
                ],
                id:[
                    { required: true, message: '请输入手机号', trigger: 'blur' },
                    { min: 1, max: 30, message: '长度在1到30个字符', trigger: 'blur' }
                ]
            },
            //修改用户
            editForm:{},
            //显示以及隐藏修改用户栏
            editFormVisible:false,
            //修改用户表单验证
            updateFormRules:{
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 1, max: 8, message: '长度在1到8个字符', trigger: 'blur' }
                ],
                password:[
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 1, max: 30, message: '长度在1到30个字符', trigger: 'blur' }
                ],
                id:[
                    { required: true, message: '请输入手机号', trigger: 'blur' },
                    { min: 1, max: 30, message: '长度在1到30个字符', trigger: 'blur' }
                ],
                email:[
                    { required: true, message: '请输入邮箱', trigger: 'blur' },
                    { min: 1, max: 50, message: '长度在1到50个字符', trigger: 'blur' }
                ],
                address:[
                    { required: true, message: '请输入地址', trigger: 'blur' },
                    { min: 1, max: 100, message: '长度在1到100个字符', trigger: 'blur' }
                ]
            }

        }
    },

    methods:{
        //返回用户列表
        async getClientList(){
            console.log("send client list")
            const {data:res} = await this.$http.get("/clientuser",{params:this.queryInfo});
            this.clientList = res.data;//用户列表数据封装
            this.total = res.number;
        },
        //最大数据量
        handleSizeChange(newSize){
            this.queryInfo.pageSize=newSize;
            this.getClientList();
        },
        //pageNum的触发动作
        handleCurrentChange(newPage){
            this.queryInfo.pageNum=newPage;
            this.getClientList();
        },
        // 监听用户操作
        addDialogClosed(){
            this.$refs.addFormRef.resetFields();
        },
        addClient(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return;

                if(this.addForm.emailnew==this.sendedCode){
                    const {data:res}=await this.$http.post("addclient",this.addForm);
                    if(res!="success"){
                        return this.$message.error("操作失败");
                    }
                    this.$message.success("操纵成功");
                    this.addDialogVisible=false;
                    this.getClientList();
                }
                
            })
        },

        //根据主键删除信息
        async deleteClient(id){
            const confirmResult = await this.$confirm('此操作将永久删除用户，是否继续?','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.info("已取消删除")
            }

            const {data:res} = this.$http.delete("deleteclient?id="+id);
            if(res=="error"){
                return this.$message.error("删除失败");
            }
            
            this.$message.success("删除成功");
            //location.reload();
            this.getClientList();
        },

        //显示对话框
        async showEditDialog(id){
            const {data:res} = await this.$http.get("updateclient?id="+id);
            this.editForm=res;
            console.log(res);
            this.editFormVisible=true;

        },
        //关闭窗口
        editDialogClosed(){
            this.$refs.editFormRef.resetFields();
        },
        //确认修改
        editClient(){
          this.$refs.editFormRef.validate(async valid =>{
              if(!valid) return;
              const {data:res} = await this.$http.put("editclient",this.editForm);
              if(res=="error") return this.$message.error("修改失败");
              this.$message.success("修改成功");
              //隐藏
              this.editFormVisible=false;
              this.getClientList();
          })  
        },
        async getTestCode(){
            this.sendPhone.phone=this.addForm.id;
            const {data:res} = await this.$http.post("sms/sendcode",this.sendPhone);
            this.$message.success("验证码已发送");
            this.show=false;
            console.log(res);
            this.sendedCode=res;
        },
    }
}
</script>

<style lang="less" scoped>

.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}


</style>