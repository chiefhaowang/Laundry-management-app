package com.example.washingstore.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alipay.api.domain.OrderPageQueryDTO;
import com.example.washingstore.bean.Order;
import com.example.washingstore.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class AdminProfit {
    @Autowired
    OrderDao orderDao;

    @RequestMapping("/getpricelist")
    public String getPriceList(){
        List<Order> orders=orderDao.getPriceList();
        int counts=orderDao.getAllOrderCounts();
        HashMap<String, Object> res = new HashMap<>();

        res.put("number",counts);
        res.put("data",orders);

        String res_string = JSON.toJSONString(res);
        return res_string;
    }
}
