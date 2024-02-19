<template>
<div>
  <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{path:'/admin'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>营业详情</el-breadcrumb-item>
            <el-breadcrumb-item>店铺流水</el-breadcrumb-item>
        </el-breadcrumb>
        
  <el-row>
    <el-button @click="printData" style="float: right; padding-right:20px;">打印数据</el-button>
    <el-container>
      <el-aside>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span><h3>月度总营收</h3></span>
            <span style="float: left; padding: 3px 0" type="text">{{this.monthTotal}}元</span>
          </div>
          <div class="personal-relation">
              <div class="relation-item">最高收入日  <div style="float: right; padding-right:20px;">{{monthHighest}}日</div></div>
          </div>
          <div class="personal-relation">
              <div class="relation-item">最低收入日  <div style="float: right; padding-right:20px;">{{monthLowest}}日</div></div>
          </div>
          <div class="personal-relation">
              <div class="relation-item">日平均收入  <div style="float: right; padding-right:20px;">{{monthAverage}}元</div></div>
          </div>
        </el-card>
        </el-aside>
        <el-main>
          <div ref="Dom" style="width:1300px; height: 300px;"></div> 
        </el-main>
      </el-container>
     
    </el-row>

    <el-row>
      <el-container>
        <el-aside>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span><h3>年度总营收</h3></span>
            <span style="float: left; padding: 3px 0" type="text">{{this.yearTotal}}元</span>
          </div>
          <div class="personal-relation">
              <div class="relation-item">最高收入月  <div style="float: right; padding-right:20px;">{{yearHighest}}月</div></div>
          </div>
          <div class="personal-relation">
              <div class="relation-item">最低收入月  <div style="float: right; padding-right:20px;">{{yearLowest}}月</div></div>
          </div>
          <div class="personal-relation">
              <div class="relation-item">月平均收入  <div style="float: right; padding-right:20px;">{{yearAverage}}元</div></div>
          </div>
        </el-card>
        </el-aside>
        <el-main>
          <div ref="DomUp" style="width:1200px; height: 300px;"></div> 
        </el-main>
      </el-container>
  </el-row>
</div>
    
</template>

<script>
  export default{
      created(){
        
      },

      mounted() {
        this.getMonthPriceList();
        this.getYearPriceList();
        // this.getTodayList();
      },

      data(){
        return{
          yearTotal:0,
          yearHighest:0,
          yearAverage:0,
          yearLowest:0,
          monthTotal:0,
          monthHighest:0,
          monthAverage:0,
          monthLowest:0
        }
      },

      methods:{

          // getTodayList(){

          //   let chartDom = this.$refs.Domup;
          //   let myChart = this.$echarts.init(chartDom);
          //   var option;

          //   option = {
          //     xAxis: {
          //       type: 'category',
          //       data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          //     },
          //     yAxis: {
          //       type: 'value'
          //     },
          //     series: [
          //       {
          //         data: [120, 200, 150, 80, 70, 110, 130],
          //         type: 'bar'
          //       }
          //     ]
          //   };

          //     option && myChart.setOption(option);
          // },

          async printData(){
            console.log("print");
            await this.$http.get("/txtprint");
          },


          async getYearPriceList(){
            const {data:res} =await this.$http.get("/getpricelist");
            var orderList=res.data;
            var number=res.number;

            let date = new Date();
            const year=date.getFullYear();
            const month=date.getMonth()+1;

            //计算card数据
            for(let i=0;i<number;i++){
              if(year==orderList[i].year){
                this.yearTotal+=orderList[i].orderpay;
              }
            }

            var numberData=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            for(let i=1;i<=12;i++){
              var total=0;
              for(let j=0;j<number;j++){
                if(orderList[j].year==year && orderList[j].month==i){
                  total+=orderList[j].orderpay;
                }
                if(total!=0){
                  numberData[i-1]=total;
                }
              }
            }
            var tem=numberData[0];
            this.yearHighest=1;
            for(let i=1;i<12;i++){
              if(numberData[i]>tem){
                this.yearHighest=i+1;
                tem=numberData[i];
              }
            }
            tem=numberData[0];
            this.yearLowest=1;
            for(let i=1;i<12;i++){
              if(numberData[i]<tem){
                this.yearLowest=i+1;
                tem=numberData[i];
              }
            }

            this.yearAverage=parseInt(this.yearTotal/month);

            let chartDom = this.$refs.DomUp;
            let myChart = this.$echarts.init(chartDom);
            var option;
            option = {
              title: {
                text: '年度收入统计',
              },
              tooltip: {
                trigger: 'axis',
                axisPointer: {
                  type: 'shadow'
                }
              },
              grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
              },
              xAxis: [
                {
                  type: 'category',
                  data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月','九月','十月','十一月','十二月'],
                  axisTick: {
                    alignWithLabel: true
                  }
                }
              ],
              yAxis: [
                {
                  type: 'value'
                }
              ],
              series: [
                {
                  name: 'Direct',
                  type: 'bar',
                  barWidth: '60%',
                  data:numberData,
                }
              ]
            };
            option && myChart.setOption(option);

            
          },

          async getMonthPriceList(){
            const {data:res} =await this.$http.get("/getpricelist");
            var orderList=res.data;
            var number=res.number;

            let date = new Date();
            const year=date.getFullYear();
            const month=date.getMonth();
            const day=date.getDate();
            console.log(year+"+"+month+"+"+day);
            ///console.log(this.orderList);
            //获取Dom节点,初始化
            let chartDom = this.$refs.Dom;
            let myChart = this.$echarts.init(chartDom);
            //绘制图标
            let option;
            // prettier-ignore
            
            var data = [["1日", 0], ["2日", 0], ["3日", 0], ["4日", 0], ["5日", 0], ["6日", 0], ["7日", 0], ["8日", 0], ["9日", 0], ["10日", 0], ["11日", 0], ["12日", 0], ["13日", 0], ["14日", 0], ["15日", 0], ["16日", 0], ["17日", 0], ["18日", 0], ["19日", 0], ["20日", 0], ["21日", 0], ["22日", 0], ["23日", 0], ["24日", 0], ["25日", 0], ["26日", 0], ["27日", 0], ["28日", 0], ["29日", 0], ["30日", 0], ["31日", 0]];
            for(let i=1;i<=31;i++){
              var total=0;
              for(let j=0;j<number;j++){
                if(orderList[j].year==year && orderList[j].month==month && orderList[j].day==i)
                  total += orderList[j].orderpay;  
              }
              if(total!=0)
                data[i-1][1]=total;
            }
            console.log(data);

            //计算card数据
            for(let i=0;i<31;i++){
              this.monthTotal+=data[i][1];
            }

            var tem=data[0][1];
            this.monthHighest=1;
            for(let i=1;i<31;i++){
              if(data[i][1]>tem){
                tem=data[i][1];
                this.monthHighest=i+1;
              }
            }

            tem=data[0][1];
            this.monthLowest=1;
            for(let i=1;i<31;i++){
              if(data[i][1]<tem){
                tem=data[i][1];
                this.monthLowest=i+1;
              }
            }
            this.monthAverage=parseInt(this.monthTotal/day);

            const dateList = data.map(function (item) {
              return item[0];
            });
            const valueList = data.map(function (item) {
              return item[1];
            });
            option = {
              visualMap: [
                {
                  show: false,
                  type: 'continuous',
                  seriesIndex: 0,
                  min: 0,
                  max: 400,
                },
                
              ],
              title: [
                {
                  text: '店铺月流水'
                },
                
              ],
              tooltip: {
                trigger: 'axis'
              },
              xAxis: [
                {
                  data: dateList
                },
                {
                  gridIndex: 1
                }
              ],
              yAxis: [
                {},
                {
                  gridIndex: 1
                }
              ],
              grid: [
                {
                  //bottom: '60%'
                },
                {
                 //top: '60%'
                }
              ],
              series: [
                {
                  type: 'line',
                  showSymbol: false,
                  data: valueList
                },
              ]
            };
            option && myChart.setOption(option);
            }
      }

  }
</script>

<style lang="less" scope>
.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 17px;
}
.personal-relation {
  font-size: 15px;
  padding: 0px 2px 5px;
  margin-right: 1px;
  width: 100%
}

.relation-item {
  padding: 8px;
}
.text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 280px;
  }
</style> 