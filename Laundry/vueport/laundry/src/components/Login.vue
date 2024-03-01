<template>
    <div class="login_container">
        <!-- 登录区域 -->
        <div class="login_box">
            <!-- logo区域 -->
            <!-- <div class="avatar_box">
                <img src="../assets/logo.png" alt/>
            </div> -->
            <!-- 表单区域 -->
            <el-form ref="loginFormRef" :model="loginForm" class="login_form"  label-width="0">
                <!-- 用户名 -->
                <el-form-item >
                    <el-input v-model="loginForm.username" prefix-icon="el-icon-user" placeholder="输入用户名"></el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item >
                    <el-input v-model="loginForm.password" prefix-icon="el-icon-edit" type="password" placeholder="输入密码"></el-input>
                </el-form-item>
                <!-- 角色登录选择 -->
                <el-form-item>
                    <el-select v-model="loginForm.role" placeholder="角色选择">
                        <el-option label="游客" value=3></el-option>
                        <el-option label="客户" value=1></el-option>
                        <el-option label="店员" value=2></el-option>
                        <el-option label="管理员" value=0></el-option>
                    </el-select>
                </el-form-item>
                <el-link type="primary" style="padding-left:10px;" :underline="false">忘记密码</el-link>
                <!-- 按钮 -->
                <el-form-item class="btns">
                    <el-button  @click="registerFormVisible=true">注册</el-button>
                    <el-button type="primary" @click="login">登录</el-button>
                </el-form-item>

            </el-form> 

        </div>
        
            <!-- 用户注册对话框 -->
            <el-dialog title="用户注册" :visible.sync="registerFormVisible" width="30%" @close="registerDialogClosed">
                <el-form :model="registerForm" :rules="registerFormRules" ref="registerFormRef" label-width="70px"> 
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="registerForm.password" placeholder="密码"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="id">
                        <el-input v-model="registerForm.id" placeholder="手机号"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" >
                        <el-input prop="code" v-model="registerForm.email" placeholder="4位验证码"></el-input>
                            <button @click="getTestCode()" :disabled="!show">
                            <span>发送验证码</span>
                            </button>
                    </el-form-item>
                </el-form>
                <!-- 底部按钮 -->
                <span slot="footer" class="dialog-footer">
                    <el-button @click="registerFormVisible=false">取 消</el-button>
                    <el-button type="primary" @click="registerUser">确 定</el-button>
                </span>
            </el-dialog>
            
    </div>
</template>

<script>
export default {
    data(){
        return{
            loginForm:{
                username:"",
                password:"",
                role:""
            },

            //用户注册部分
            registerFormVisible:false,
            registerForm:{
                username:"",
                password:"",
                id:"",
                email:""//仅仅用来承接验证码
            },

            sendPhone:{
                phone:""
            },

            //添加表单验证
            registerFormRules: {
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
                    { min: 11, max: 11, message: '长度在11个字符', trigger: 'blur' }
                ],
            },
            //验证码
            sendedCode:"",
            count:"",
            show:true,
        }
    },
    methods:{
        //重置表单
        register(){
            console.log("register");
            
        },
        //关闭窗口
        registerDialogClosed(){
            this.$refs.editFormRef.resetFields();
        },
        //登录处理
        login(){
            //校验
            this.$refs.loginFormRef.validate(async valid=>{
                console.log(valid);
                if(!valid) {
                    console.log("Login Failed!");
                    return;
                }
                const {data:res} = await this.$http.post("login",this.loginForm);//data重命名为res，访问后台
                console.log(res);
                if(res.flag == "ok"){
                    window.sessionStorage.setItem("user",JSON.stringify(res.user));//前端暂时存储user对象
                    console.log(res.user);

                    //路由跳转
                    if(res.user.role==0){
                        
                        this.$router.push({path:"/admin"});
                        this.$message.success("管理员端登录");
                        console.log("Login admin");
                        }
                    else if(res.user.role==1){
                        
                        this.$router.push({path:"/client"});
                        this.$message.success("客户端登录");
                        console.log("Login client");
                        }
                    else if(res.user.role==3){
                        this.$router.push({path:"/tourist"});
                        this.$message.success("游客端登录");
                        console.log("Login tourist");
                        }
                    else{
                        this.$router.push({path:"/clerk"});
                        this.$message.success("店员端登录");
                        console.log("Login clerk");
                        }
                }else{
                    this.$message.console.error("Error!");
                }
            })
        },

        async getTestCode(){
            this.sendPhone.phone=this.registerForm.id;
            const {data:res} = await this.$http.post("sms/sendcode",this.sendPhone);
            this.$message.success("验证码已发送");
            this.show=false;
            console.log(res);
            this.sendedCode=res;
            
        },

        async registerUser(){
            this.$refs.loginFormRef.validate(async valid=>{
                if(!valid) {
                    return;
                }
                if(this.registerForm.email==this.sendedCode){
                    const {data:res} = await this.$http.post("/register",this.registerForm);
                    if(res=="ok"){
                        this.$message.success("注册成功！");
                        
                    }else{
                        this.$message.error("注册失败");
                        
                    }
                    this.registerFormVisible=false;
                }
                else{
                    this.$message.error("验证码错误，请重新获取验证码");
                }
                
            })
        }

    }
}
</script>

<style lang="less" scoped>
//根节点样式
.login_container{
    background-color: #2b4b6b;
    height: 100%;
}
.login_box{
    background-color: #2b4b6b;
    width: 450px;
    height: 330px;
    background-color: #fff;
    border-radius: 3px;
    position: absolute;
    left:50%;
    top:50%;
    transform: translate(-50%,-50%);
    // .avatar_box{
    //     width:130px;
    //     height:130px;
    //     border: 1px solid #eee;
    //     border-radius: 50%;
    //     padding: 10px;
    //     box-shadow: 0 0 10px #ddd;
    //     position: absolute;
    //     left:50%;
    //     transform: translate(-50%,-50%);
    //     background-color: #0ee;
    //     img{
    //         width: 100%;
    //         height: 100%;
    //         border-radius: 50%;
    //         background-color: #eee;
    //     }
    // }
}
.btns{
    display: flex;
    justify-content: flex-end;
}
.login_form{
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}

</style>