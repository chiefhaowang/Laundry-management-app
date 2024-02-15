package com.example.washingstore.controller.clerk;

import com.alibaba.fastjson.JSON;
import com.example.washingstore.bean.Order;
import com.example.washingstore.bean.QueryInfo;
import com.example.washingstore.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ClerkHisOrder {
    @Autowired
    private OrderDao orderDao;
    @RequestMapping("/clerkhisorder")
    public String getHisOrderList(QueryInfo queryInfo){

        int orderCounts=orderDao.getOrderCounts("%"+queryInfo.getQuery()+"%");//模糊查询
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<Order> orderList=orderDao.getAllOrder("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());

        System.out.println(orderCounts);

        HashMap<String, Object> map=new HashMap<>();

        map.put("number",orderCounts);
        map.put("data",orderList);

        String res= JSON.toJSONString(map);
        return res;
    }
}
