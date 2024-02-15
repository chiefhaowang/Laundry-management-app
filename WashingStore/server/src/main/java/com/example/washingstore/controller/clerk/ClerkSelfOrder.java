package com.example.washingstore.controller.clerk;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.ChargeOrder;
import com.example.washingstore.bean.Order;
import com.example.washingstore.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClerkSelfOrder {
    @Autowired
    OrderDao orderDao;
    @RequestMapping("/stateonelist")
    public String getStateOneList(Order order){
        List<Order> orders=orderDao.getStateOneOrder(order.getFirst_clerkname(),order.getFirst_clerkphone());
        String res= JSON.toJSONString(orders);
        return res;
    }

    @RequestMapping("/statetwolist")
    public String getStateTwoList(Order order){
        List<Order> orders=orderDao.getStateTwoOrder(order.getSecond_clerkname(),order.getSecond_clerkphone());

        String res= JSON.toJSONString(orders);
        return res;
    }

    @RequestMapping("/statethreelist")
    public String getStateThreeList(Order order){
        List<Order> orders=orderDao.getStateThreeOrder(order.getThird_clerkname(),order.getThird_clerkphone());

        String res= JSON.toJSONString(orders);
        return res;
    }

    @RequestMapping("/editorderstate")
    public String editState(@RequestBody Order order){
        int i=orderDao.editOrderState(order);
        return i>0?"success":"error";
    }

    @RequestMapping("/orderincharge")
    public String OrderInCharge(@RequestBody ChargeOrder chargeOrder){
        Order order = new Order();
        int i = 0;
        System.out.println("order id:"+chargeOrder.getState());
        order.setId(chargeOrder.getId());
        if(chargeOrder.getState()==1){
            order.setFirst_clerkname(chargeOrder.getClerkname());
            order.setFirst_clerkphone(chargeOrder.getClerkphone());
            i=orderDao.OrderInChargeOne(order);
        }else if(chargeOrder.getState()==2){
            order.setSecond_clerkname(chargeOrder.getClerkname());
            order.setSecond_clerkphone(chargeOrder.getClerkphone());
            i=orderDao.OrderInChargeTwo(order);
        }else if(chargeOrder.getState()==3){
            order.setThird_clerkname(chargeOrder.getClerkname());
            order.setThird_clerkphone(chargeOrder.getClerkphone());
            i=orderDao.OrderInChargeThree(order);
        }
        System.out.println(order.getFirst_clerkname());

        return i>0?"success":"error";
    }

    @RequestMapping("/endorder")
    public String endOrder(@RequestBody Order order){
        System.out.println(order.getId());
        int i=orderDao.endOrder(order);
        return i>0?"success":"error";
    }
}
