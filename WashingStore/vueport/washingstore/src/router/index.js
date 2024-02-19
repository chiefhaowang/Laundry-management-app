import Vue from 'vue'
import VueRouter from 'vue-router'

//登录页面
import Login from '../components/Login.vue'
//管理员页面
import Admin from "../components/Admin.vue"
import AdminWelcome from "../components/admin/AdminWelcome.vue"
import AdminClientList from "../components/admin/AdminClientList.vue"
import AdminClerkList from "../components/admin/AdminClerkList.vue"
import AdminPriceSet from "../components/admin/AdminPriceSet.vue"
import AdminOrderSearch from "../components/admin/AdminOrderSearch.vue"
import AdminProfit from "../components/admin/AdminProfit.vue"
import AdminClerkExpress from "../components/admin/AdminClerkExpress.vue"
import AdminSelfIfo from "../components/admin/AdminSelfIfo.vue"
//客户页面
import Client from "../components/Client.vue"
import ClientWelcome from "../components/client/ClientWelcome.vue"
import ClientSelfIfo from "../components/client/ClientSelfIfo.vue"
import ClientOrder from "../components/client/ClientOrder.vue"
import ClientHisOrder from "../components/client/ClientHisOrder.vue"
import ClientActivity from "../components/client/ClientActivity.vue"
import ClientOrderStuff from "../components/client/ClientOrderStuff.vue"
//店员页面
import Clerk from "../components/Clerk.vue"
import ClerkWelcome from "../components/clerk/ClerkWelcome.vue"
import ClerkSelfIfo from "../components/clerk/ClerkSelfIfo.vue"
import ClerkActivity from "../components/clerk/ClerkPlaceSearch.vue"
import ClerkOrder from "../components/clerk/ClerkOrder.vue"
import ClerkHisOrder from "../components/clerk/ClerkHisOrder.vue"
import ClerkSelfOrder from "../components/clerk/ClerkSelfOrder.vue"

//游客界面
import Tourist from "../components/Tourist.vue"
import TouristWelcome from "../components/tourist/TouristWelcome.vue"
import TouristList from "../components/tourist/TouristList.vue"
import TouristStuffList from "../components/tourist/TouristStuffList.vue"


Vue.use(VueRouter)
const routes = [
  {
    path:"/",
    redirect:"/Login"

  },
  {
    path:"/login",
    component:Login
  },
  {
    path:"/admin",
    component:Admin,
    redirect:"/adminwelcome",
    children:[
      {path:"/adminwelcome",component:AdminWelcome},
      {path:"/adminclientlist",component:AdminClientList},
      {path:"/adminclerklist",component:AdminClerkList},
      {path:"/adminpriceset",component:AdminPriceSet},
      {path:"/adminordersearch",component:AdminOrderSearch},
      {path:"/adminprofit",component:AdminProfit},
      {path:"/adminclerkexpress",component:AdminClerkExpress},
      {path:"/adminselfifo",component:AdminSelfIfo},
      ]
  },
  {
    path:"/client",
    component:Client,
    redirect:"/clientwelcome",
    children:[
      {path:"/clientwelcome",component:ClientWelcome},
      {path:"/clientselfifo",component:ClientSelfIfo},
      {path:"/clientorder",component:ClientOrder},
      {path:"/clienthisorder",component:ClientHisOrder},
      {path:"/clientactivity",component:ClientActivity},
      {path:"/clientorderstuff",component:ClientOrderStuff}
    ]
  },
  {
    path:"/clerk",
    component:Clerk,
    redirect:"/clerkwelcome",
    children:[
      {path:"/clerkwelcome",component:ClerkWelcome},
      {path:"/clerkselfifo",component:ClerkSelfIfo},
      {path:"/clerkactivity",component:ClerkActivity},
      {path:"/clerkorder",component:ClerkOrder},
      {path:"/clerkhisorder",component:ClerkHisOrder},
      {path:"/clerkselforder",component:ClerkSelfOrder}
    ]
  },
  {
    path:"/tourist",
    component:Tourist,
    redirect:"/touristwelcome",
    children:[
      {path:"/touristwelcome",component:TouristWelcome},
      {path:"/touristlist",component:TouristList},
      {path:"/touriststufflist",component:TouristStuffList},
    ]
  }


]


const router = new VueRouter({
  routes
})

// 问题解决
// const originaPush = VueRouter.prototype.push
// VueRouter.prototype.push = function push(location,onResolve, onReject){
//   if(onReject || onResolve) return originaPush.call(this, location, onResolve, onReject)
//   return originaPush.call(this, location).catch(err => err)
// }


//路由守卫：确保信息流的存在
router.beforeEach((to,from,next)=>{
  //to将要访问
  //from从哪里访问
  //next接着做  next(url)重定向到url上 next（）继续访问to的路径
  if(to.path =='/login') return next();

  //获取user
  const userFlag = window.sessionStorage.getItem("user");//取出当前用户
  if(!userFlag) {
    console.log("no message flow!");
    return next('/login');
  }//无值返回登录页
  next();//符合要求 放行
})
export default router
