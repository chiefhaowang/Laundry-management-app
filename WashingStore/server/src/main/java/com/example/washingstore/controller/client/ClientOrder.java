package com.example.washingstore.controller.client;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.Order;
import com.example.washingstore.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//处理客户端的订单显示
@RestController
public class ClientOrder {

    @Autowired
    OrderDao orderDao;
    @RequestMapping("/orderlist")
    public String getOrderList(String phone){
        List<Order> orders=orderDao.getOrderActived("%"+phone+"%");

        String res= JSON.toJSONString(orders);
        return res;
    }

    @RequestMapping("/orderhislist")
    public String getHisOrder(String phone){
        List<Order> orderList=orderDao.getHisOrder("%"+phone+"%");
        String res= JSON.toJSONString(orderList);
        System.out.println(phone);
        return res;
    }

    @RequestMapping("/addorder")
    public String addOrder(@RequestBody Order order){
        int i=orderDao.addOrder(order);
        return i>0?"success":"error";
    }

}
