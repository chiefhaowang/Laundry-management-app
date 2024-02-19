<template>
    <div>
        <el-tabs  stretch>
            <el-tab-pane label="衣物定价">
                <el-card>
                    <el-row :gutter="25">
                        <el-col :span="10">
                            <!-- 搜索区域 -->
                            <el-input placeholder="输入衣物名称" v-model="queryInfoClothe.query" clearable @clear="getClothePriceList">

                            <el-button slot="append" icon="el-icon-search" @click="getClothePriceList"></el-button>
                            </el-input>
                        </el-col>
                        <el-col :span="4">
                            <el-button type="primary" @click="addClotheDialogVisible=true">添加衣物</el-button>
                        </el-col>
                    </el-row>   
                    <!-- 客户列表展示 -->
                    <el-table :data="clothePriceList" border stripe>
                        <!-- 索引 -->
                        <el-table-column type="index"></el-table-column>
                        <el-table-column label="衣物名称" prop="name"></el-table-column>
                        <el-table-column label="尺码（小）" prop="shorter"></el-table-column>
                        <el-table-column label="尺码（中）" prop="mid"></el-table-column>
                        <el-table-column label="尺码（大）" prop="longer"></el-table-column>
                        <el-table-column label="尺码（加长）" prop="suplong"></el-table-column>
                        <el-table-column label="操作">
                            <!-- slot-scope="scope" -->
                            <template slot-scope="scope">
                                <!-- 修改 -->
                                <el-tooltip effect="dark" content="修改价格" placement="top-start" :enterable="false">
                                <el-button @click="showEditClotheDialog(scope.row.name)" type="primary" icon="el-icon-edit" size="mini" ></el-button>
                                </el-tooltip>
                                <!-- 删除 -->
                                <el-tooltip effect="dark" content="删除衣物" placement="top-start" :enterable="false">
                                <el-button @click="deleteClothe(scope.row.name)" type="danger" icon="el-icon-delete" size="mini"></el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 分页组件 -->
                    <div>
                        <el-pagination @size-change="handleSizeChange_Clothe"
                            @current-change="handleCurrentChange_Clothe"
                            :current-page="queryInfoClothe.number"
                            :page-sizes="[1, 2, 5, 10]"
                            :page-size="queryInfoClothe.pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="clotheTotal">
                        </el-pagination>
                    </div>
                </el-card>
            </el-tab-pane>
            <el-tab-pane label="物品定价">
                <el-card>
                    <el-row :gutter="25">
                        <el-col :span="10">
                            <!-- 搜索区域 -->
                            <el-input placeholder="搜索物品名称" v-model="queryInfoStuff.query" clearable @clear="getStuffPriceList">

                            <el-button slot="append" icon="el-icon-search" @click="getStuffPriceList"></el-button>
                            </el-input>
                        </el-col>
                        <el-col :span="4">
                            <el-button type="primary" @click="addStuffDialogVisible=true">添加物品</el-button>
                        </el-col>
                    </el-row>   
                    <!-- 客户列表展示 -->
                    <el-table :data="stuffPriceList" border stripe>
                        <!-- 索引 -->
                        <el-table-column type="index"></el-table-column>
                        <el-table-column label="衣物名称" prop="name"></el-table-column>
                        <el-table-column label="尺码（小）" prop="small"></el-table-column>
                        <el-table-column label="尺码（中）" prop="mid"></el-table-column>
                        <el-table-column label="尺码（大）" prop="big"></el-table-column>
    
                        <el-table-column label="操作">
                            <!-- slot-scope="scope" -->
                            <template slot-scope="scope">
                                <!-- 修改 -->
                                <el-tooltip effect="dark" content="修改价格" placement="top-start" :enterable="false">
                                <el-button @click="showEditStuffDialog(scope.row.name)" type="primary" icon="el-icon-edit" size="mini" ></el-button>
                                </el-tooltip>
                                <!-- 删除 -->
                                <el-tooltip effect="dark" content="删除衣物" placement="top-start" :enterable="false">
                                <el-button @click="deleteStuff(scope.row.name)" type="danger" icon="el-icon-delete" size="mini"></el-button>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!-- 分页组件 -->
                    <div>
                        <el-pagination @size-change="handleSizeChange_Stuff"
                            @current-change="handleCurrentChange_Stuff"
                            :current-page="queryInfoStuff.number"
                            :page-sizes="[1, 2, 5, 10]"
                            :page-size="queryInfoStuff.pageSize"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="stuffTotal">
                        </el-pagination>
                    </div>
                </el-card>
            </el-tab-pane>
        </el-tabs>
        
        <el-dialog title="添加衣物" :visible.sync="addClotheDialogVisible" width="50%" @close="addClotheDialogClosed">
            <el-form :model="addClotheForm"  ref="addClotheFormRef" label-width="100px"> 
                <el-form-item label="衣物名称" prop="name">
                    <el-input v-model="addClotheForm.name"></el-input>
                </el-form-item>
                <el-form-item label="尺码（小）" prop="shorter">
                    <el-input v-model="addClotheForm.shorter"></el-input>
                </el-form-item>
                <el-form-item label="尺码（中）" prop="mid">
                    <el-input v-model="addClotheForm.mid"></el-input>
                </el-form-item>
                <el-form-item label="尺码（大）" prop="longer">
                    <el-input v-model="addClotheForm.longer"></el-input>
                </el-form-item>
                <el-form-item label="尺码（加长）" prop="suplong">
                    <el-input v-model="addClotheForm.suplong"></el-input>
                </el-form-item>
                
            </el-form>
            
            <span slot="footer" class="dialog-footer">
                <el-button @click="addClotheDialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="addClothe">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改衣物信息" :visible.sync="editClotheDialogVisible" width="50%" @close="editClotheDialogClosed">
            <el-form :model="editClotheForm" ref="editClotheFormRef" label-width="100px">
                <el-form-item label="衣物名称" prop="name">
                    <el-input v-model="editClotheForm.name" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="尺码（小）" prop="shorter">
                    <el-input v-model="editClotheForm.shorter"></el-input>
                </el-form-item>
                <el-form-item label="尺码（中）" prop="mid">
                    <el-input v-model="editClotheForm.mid"></el-input>
                </el-form-item>
                <el-form-item label="尺码（大）" prop="longer">
                    <el-input v-model="editClotheForm.longer"></el-input>
                </el-form-item>
                <el-form-item label="尺码（加长）" prop="suplong">
                    <el-input v-model="editClotheForm.suplong"></el-input>
                </el-form-item>
                
            </el-form>
            
            <span slot="footer" class="dialog-footer">
                <el-button @click="editClotheDialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="editClothe">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="添加清洗物品" :visible.sync="addStuffDialogVisible" width="50%" @close="addStuffDialogClosed">
            <el-form :model="addStuffForm"  ref="addStuffFormRef" label-width="100px"> 
                <el-form-item label="物品名称" prop="name">
                    <el-input v-model="addStuffForm.name"></el-input>
                </el-form-item>
                <el-form-item label="尺码（小）" prop="small">
                    <el-input v-model="addStuffForm.small"></el-input>
                </el-form-item>
                <el-form-item label="尺码（中）" prop="mid">
                    <el-input v-model="addStuffForm.mid"></el-input>
                </el-form-item>
                <el-form-item label="尺码（大）" prop="big">
                    <el-input v-model="addStuffForm.big"></el-input>
                </el-form-item>
                
            </el-form>
            
            <span slot="footer" class="dialog-footer">
                <el-button @click="addStuffDialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="addStuff">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改物品信息" :visible.sync="editStuffDialogVisible" width="50%" @close="editStuffDialogClosed">
            <el-form :model="editStuffForm" ref="editStuffFormRef" label-width="100px">
                <el-form-item label="物品名称" prop="name">
                    <el-input v-model="editStuffForm.name" disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="尺码（大）" prop="small">
                    <el-input v-model="editStuffForm.small"></el-input>
                </el-form-item>
                <el-form-item label="尺码（大）" prop="mid">
                    <el-input v-model="editStuffForm.mid"></el-input>
                </el-form-item>
                <el-form-item label="尺码（大）" prop="big">
                    <el-input v-model="editStuffForm.big"></el-input>
                </el-form-item>
                
            </el-form>
            
            <span slot="footer" class="dialog-footer">
                <el-button @click="editStuffDialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="editStuff">确 定</el-button>
            </span>
        </el-dialog>
        
    </div>
</template>

<script>
export default {
    created(){
        this.getClothePriceList();
        this.getStuffPriceList();
    },

    data(){
        return{

            clothePriceList:[],
            stuffPriceList:[],
            //添加衣物
            addClotheDialogVisible:false,
            editClotheDialogVisible:false,
            //查询信息实体
            queryInfoClothe:{
                query:"",//查询信息
                pageNum:1,//当前页面
                pageSize:10,//每页最大数
            },
            clotheTotal:0,//总记录数量,
            addClotheForm:{
                name:"",
                shorter:"",
                mid:"",
                longer:"",
                suplong:""
            },
            editClotheForm:{},

            addStuffDialogVisible:false,
            editStuffDialogVisible:false,
            //查询信息实体
            queryInfoStuff:{
                query:"",//查询信息
                pageNum:1,//当前页面
                pageSize:10,//每页最大数
            },
            stuffTotal:0,//总记录数量,
            addStuffForm:{
                name:"",
                shorter:"",
                mid:"",
                longer:"",
                suplong:""
            },
            editStuffForm:{},

            
        }
    },

    methods:{

        async getClothePriceList(){
            const {data:res} = await this.$http.get("/setclotheprice",{params:this.queryInfoClothe});
            console.log(111111);
            this.clothePriceList = res.data;//用户列表数据封装
            this.clotheTotal = res.number;
        },
        async getStuffPriceList(){
            const {data:res2} = await this.$http.get("/setstuffprice",{params:this.queryInfoStuff});
            console.log(222222);
            this.stuffPriceList = res2.data;//用户列表数据封装
            this.stuffTotal = res2.number;
        },
        
        //衣物
        handleSizeChange_Clothe(newSize){
            this.queryInfoClothe.pageSize=newSize;
            this.getClothePriceList();
        },
        
        handleCurrentChange_Clothe(newPage){
            this.queryInfoClothe.pageNum=newPage;
            this.getClothePriceList();
        },

        async addClothe(){
            this.$refs.addClotheFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res}=await this.$http.post("/addclotheprice",this.addClotheForm);
                if(res!="success"){
                    return this.$message.error("操作失败");
                }
                this.$message.success("操纵成功");
                this.addClotheDialogVisible=false;
                location.reload();
            })
        },

        async showEditClotheDialog(name){
            const {data:res} = await this.$http.get("getupdateclotheprice?name="+name);
            this.editClotheForm=res;
            this.editClotheDialogVisible=true;
        },

        async editClothe(){
            this.$refs.editClotheFormRef.validate(async valid =>{
              if(!valid) return;
              const {data:res} = await this.$http.put("editclotheprice",this.editClotheForm);
              if(res=="error") return this.$message.error("修改失败");
              this.$message.success("修改成功");
              //隐藏
              this.editClotheFormVisible=false;
              location.reload();
          }) 
        },

        async deleteClothe(name){
            const confirmResult = await this.$confirm('此操作将永久删除衣物价格信息，是否继续?','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.info("已取消删除")
            }

            const {data:res} = this.$http.delete("deleteclotheprice?name="+name);
            if(res=="error"){
                return this.$message.error("删除失败");
            }
            
            this.$message.success("删除成功");
            location.reload();
            
        },

        addClotheDialogClosed(){
            this.$refs.addClotheFormRef.resetFields();
        },

        editClotheDialogClosed(){
            this.$refs.editClotheFormRef.resetFields();
        },
        
        //物品
        handleSizeChange_Stuff(newSize){
            this.queryInfoStuff.pageSize=newSize;
            this.getStuffPriceList();
        },
       
        handleCurrentChange_Stuff(newPage){
            this.queryInfoStuff.pageNum=newPage;
            this.getStuffPriceList();
        },
      
        async addStuff(){
            this.$refs.addStuffFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res}=await this.$http.post("/addstuffprice",this.addStuffForm);
                if(res!="success"){
                    return this.$message.error("操作失败");
                }
                this.$message.success("操纵成功");
                this.addStuffDialogVisible=false;
                location.reload();
            })
        },

        async showEditStuffDialog(name){
            const {data:res} = await this.$http.get("getupdatestuffprice?name="+name);
            this.editStuffForm=res;
            this.editStuffDialogVisible=true;
        },

        async editStuff(){
            this.$refs.editStuffFormRef.validate(async valid =>{
              if(!valid) return;
              const {data:res} = await this.$http.put("editstuffprice",this.editStuffForm);
              if(res=="error") return this.$message.error("修改失败");
              this.$message.success("修改成功");
              //隐藏
              this.editStuffFormVisible=false;
              location.reload();
          }) 
        },

        async deleteStuff(name){
            const confirmResult = await this.$confirm('此操作将永久删除物品价格信息，是否继续?','提示',{
                confirmButtonText:'确定',
                canselButtonText:'取消',
                type:'warning'
            }).catch(err=>err)
            if(confirmResult!='confirm'){//取消操作
                return this.$message.info("已取消删除")
            }

            const {data:res} = this.$http.delete("deletestuffprice?name="+name);
            if(res=="error"){
                return this.$message.error("删除失败");
            }
            
            this.$message.success("删除成功");
            location.reload();
        },

        addStuffDialogClosed(){
            this.$refs.addStuffFormRef.resetFields();
        },

        editStuffDialogClosed(){
            this.$refs.editStuffFormRef.resetFields();
        },
    }
}
</script>

<style lang="less" scoped>
.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}
::v-deep .el-tabs__nav-scroll{
  width: 50%!important;
  margin: 0 auto!important;
}
.el-card{
    background-color: #eaedf1;
}
</style>