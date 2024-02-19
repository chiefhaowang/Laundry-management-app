<template>
    <div style="margin-top: 10px">
        <div style="height:520px;">
            <div id="all" style="height:100%">
                <div class="posInput">
                    <el-input style="width:100%"
                                id="tipinput"
                                class="form-control input-style"
                                type="text"
                                placeholder="请输入搜索地址"
                                prefix-icon="el-icon-search"
                                v-model="MapAdress"
                                >
                    </el-input>
                </div>
                <div id="allmap"></div>
                <div class="posSubmit">
                        <el-form  ref="form"  label-width="85px" >
                        <div class="btn_box" >
                                    <el-form-item label="地址坐标:" >
                                        <el-input style="width:400px" disabled class="form-control input-style" type="text" v-model="insureAdress"> </el-input>
                                        </el-form-item>
                                        <el-form-item label="签到地址:" >
                                        <el-input style="width:400px"  disabled class="form-control input-style" type="text" v-model="insureAdress2"> </el-input>
                                    </el-form-item>
                            </div>
                        </el-form>    
                    
                    
                </div>
            </div>
        </div>
</div>

</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader';
	export default {
		name: 'demo',
        props:{
          adressData:{
              type:Object,
              default:()=>{}
          }
        },
		data() {
			return {
				map: null,
                marker:null,
                geocoder: null, // 地理编码与逆地理编码类，用于地址描述与坐标之间的转换
                startAutoComplete: null,
                startPlaceSearch: null,
                positionPicker:null,
                startSeacrh:[],
                stratInfo:{},

				thisPosition: {
					location: '',
					lng: '',
					lat: ''
				},
				mapinitCode:0,//地理编码:1;地理逆编码:2
				MapAdress:'',
				insureAdress:'',
                insureAdress2:''
			}
		},
        mounted() {
			//我这里的经纬度是从路由上带过来的，如果路由上有经纬度，取经纬度；
			//如果路由上没有经纬度，则取路由上带过来的详细地址（address）使用地理逆编码转换为经纬度定位到地图上
			//tips：使用地理逆编码，此时解析出的经纬度位置也应逆换
			// if(adressData.longitute){
			// 		console.log("有经纬度")
			// 		this.mapinitCode = 1;
			// 		this.mapinit(105.602725,37.076636)
			// 	}else{
			// 		console.log("无经纬度")
			// 		this.mapinitCode = 2;
			// 		this.maplocal(this.$route.query.rowData.address)
			// }
            this.loadmap()
          
		},
   methods: {
     loadmap() {
        const that = this
        return new Promise((reslove, reject) => {
            AMapLoader.load({
            key: "ef51246cf5224fe87071a5a2d2400bf9", // 申请好的Web端开发者Key，首次调用 load 时必填
            //version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
            plugins: [
               'AMap.ToolBar', 'AMap.Scale','AMap.Geocoder'
            ], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
            AMapUI: {
                // 是否加载 AMapUI，缺省不加载
                version: "1.1", // AMapUI 缺省 1.1
                plugins: [], // 需要加载的 AMapUI ui插件
            },
            })
            .then((AMap) => {
                that.map = new AMap.Map("allmap", {
                    resizeEnable: true,
                    zoom: 4,
                 center: [116.2317, 39.5427],//中心点坐标
                // pitch: 4, // 地图俯仰角度，有效范围 0 度- 83 度
                })
                //给地图新增缩放条，和 地图左下角的海里控件
                that.map.addControl(new AMap.Scale())
                that.map.addControl(new AMap.ToolBar())
                // that.map.setMapStyle("amap://styles/blue")
                // that.map.setMapStyle("amap://styles/darkblue")
                // 地理编码与逆地理编码类，用于地址描述与坐标之间的转换
                that.geocoder = new AMap.Geocoder()
                 //构造地点查询类 -开始
                AMap.plugin(["AMap.PlaceSearch", "AMap.Autocomplete"], function () {
                    let autoOptions1 = { input: "tipinput", city: "全国" }
                    that.startAutoComplete = new AMap.Autocomplete(autoOptions1)
                    that.startPlaceSearch = new AMap.PlaceSearch({
                    map: that.map,
                })
                that.startAutoComplete.on("select", that.handleStartSelect) //注册监听，当选中某条记录时会触发
          
            })
                // that.handlePositionPickerEvent() 实现地图拖拽选址功能

                that.map.setZoom(6)
       
                reslove()
            })
            .catch((e) => {
                console.log(e, "高德地图加载失败")
                reject(e)
            })
        })
          
    },
    handlePositionPickerEvent(){
        let that = this;
         AMapUI.loadUI(['misc/PositionPicker'], function (PositionPicker) {
             console.log('拖拽加载成功');
              that.positionPicker = new PositionPicker({
                // mode: 'dragMap',
                mode: 'dragMarker',
                map: that.map
             })
              
           that.positionPicker.on('success', function (positionResult) {
                     console.log(positionResult,'positionResult');
          })
           that.positionPicker.on('fail', function (positionResult) {
            // 海上或海外无法获得地址信息
          
            })


         })

    },
    handleStartSelect(event){
            console.log(event, "起点经纬度 [lng,lat]")
            if (event.poi.location == "") {
                this.$message({
                type: "warning",
                message: "该地点无经纬度数据，请输入具体一点的地点!",
                duration: 5 * 1000,
                })
                return
            } 
            if(this.marker){
                 this.map.remove(this.marker)
                 this.marker =null;
            }
           this.startSeacrh =[];
           this.stratInfo = {};
            this.startSeacrh = [event.poi.location.lng, event.poi.location.lat]
            this.stratInfo = {
                district: event.poi.district,
                address: event.poi.address,
                name: event.poi.name,
            }
            this.insureAdress = event.poi.district+event.poi.address+event.poi.name
            this.insureAdress2 = '['+event.poi.location.lng+','+event.poi.location.lat+']'
            this.marker = new AMap.Marker({
                position: this.startSeacrh//位置
            })
            this.map.add(this.marker);//添加到地图
                // 传入经纬度，设置地图中心点
            this.map.setCenter(this.startSeacrh);
            // this.positionPicker.start(this.startSeacrh)  启动这句控制 地图出现一个maker点，可以移动
            // this.positionPicker.stop()   取消移动的maker
           this.map.setZoom(15)

    },

			insureMapAdress(){//确定修改地址
				console.log(this.insureAdress)
				this.$confirm('此操作为修改地址, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let data = {}
					data.id=this.rowData.id;
					data.longitute = this.thisPosition.lng
					data.latitude = this.thisPosition.lat
					data.address = this.insureAdress
					// updateCompanyAddress(data).then(res => {
					// 	if(res.data.code ==  1){
					// 		this.$message({
					// 			message: '更新地址成功！',
					// 			type: 'success'
					// 		});
					// 	}else{
					// 		this.$message.error('修改失败');
					// 	}

					// })
				})
			},
	
			maplocal (address){ //地理逆编码
				this.geocoder.getLocation(address, (status, result) => {
							console.log(address);
							if (status === 'complete' && result.geocodes.length) {
								console.log(result)
								const lnglat = result.geocodes[0].location;

								const lat = lnglat.lat;
								const lng = lnglat.lng;
								that.mapinit(lat, lng)	//tips：使用地理逆编码，此时解析出的经纬度位置也应逆换
							} else {
								console.log(result)
							}
					});
			},
		},
			
	}
</script>

<style lang="less" scope>
#all{
        position: relative;
        
    }
    #allmap{
        width: 100%;  height: 100%;
        font-family: "微软雅黑";
    }
    .posInput{
        position: absolute;	z-index: 1;
        width: 80%;
        margin-top: 20px;  margin-left: 10%;
    }
    .posSubmit{
        position: absolute; z-index: 1; bottom: 0;
        margin-left: 5%;
        width: 90%;
        display: flex;  justify-content: flex-start; align-items: center;
    }
    .btn_box{
        width: 100%;
        height: 100%;
       display: flex;  ; align-items: center;
    }
    ::v-deep .el-form-item{
            margin-bottom: 0 !important;
    }
    

</style>