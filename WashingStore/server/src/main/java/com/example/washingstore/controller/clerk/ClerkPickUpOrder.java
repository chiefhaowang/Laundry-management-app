package com.example.washingstore.controller.clerk;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.Order;
import com.example.washingstore.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
public class ClerkPickUpOrder {
    @Autowired
    OrderDao orderDao;

    @RequestMapping("/freeoneorder")
    public String getFreeOneOrderList(){
        List<Order> orders=orderDao.getFreeOneOrder();
        String res= JSON.toJSONString(orders);
        return res;
    }

    @RequestMapping("/freetwoorder")
    public String getFreeTwoOrderList(){
        List<Order> orders=orderDao.getFreeTwoOrder();
        String res= JSON.toJSONString(orders);
        return res;
    }

    @RequestMapping("/freethreeorder")
    public String getFreeThreeOrderList(){
        List<Order> orders=orderDao.getFreeThreeOrder();
        String res= JSON.toJSONString(orders);
        return res;
    }
}
